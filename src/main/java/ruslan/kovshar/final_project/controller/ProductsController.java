package ruslan.kovshar.final_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.ProductInCheck;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.exceptions.ProductNotFoundException;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.service.CheckService;
import ruslan.kovshar.final_project.service.ProductService;
import ruslan.kovshar.final_project.service.StockService;

import java.util.Optional;

@Controller
public class ProductsController {

    private final ProductService productService;
    private final CheckService checkService;
    private final StockService stockService;
    private Check check;

    public ProductsController(ProductService productService, CheckService checkService, StockService stockService) {
        this.productService = productService;
        this.checkService = checkService;
        this.stockService = stockService;
    }

    @GetMapping("/check")
    public String getCheckPage(@RequestParam(name = "notFound", required = false) String notFound,
                               Model model) {
        if (check != null) {
            model.addAttribute("check", check);
        }
        model.addAttribute("notFound", notFound != null);
        return "check";
    }

    @GetMapping("/check/product/{id}")
    public String getProductPage(@PathVariable(name = "id", required = false) Product product,
                                 @RequestParam(name = "error", required = false) String error,
                                 Model model) {

        model.addAttribute("product", product);
        model.addAttribute("error", error != null);
        return "product";
    }

    @PostMapping("/check/product")
    public String findProduct(String name) {
        Integer code = null;
        try {
            code = Integer.parseInt(name);
        } catch (NumberFormatException ignored) {
        }

        try {
            Product product = productService.loadByCodeOrName(code, name);
            return "redirect:/check/product/" + product.getId();
        } catch (ProductNotFoundException e) {
            return "redirect:/check?notFound";
        }
    }

    @GetMapping("/open_check")
    public String openCheck() {
        if (check == null) {
            check = new Check();
        }
        return "redirect:/check";
    }

    @PostMapping("/close_check")
    public String closeCheck(@AuthenticationPrincipal User user) {
        check.setUser(user);
        user.getChecks().add(check);
        checkService.saveCheck(check);
        check = null;
        return "redirect:/";
    }

    @PostMapping("/product/add/{id}")
    public String addProductToCheck(@PathVariable(name = "id") Product product, Number number) {
        try {
            stockService.update(product, -number.intValue());
        } catch (TransactionException e) {
            return "redirect:/check/product/" + product.getId() + "?error";
        }

        ProductInCheck productInCheck = new ProductInCheck();
        productInCheck.setProduct(product);

        if (product.getClass().equals(ruslan.kovshar.final_project.entity.CountProduct.class)) {
            productInCheck.setValue(number.intValue());
        } else {
            productInCheck.setValue(number.doubleValue());
        }

        productInCheck.setPrice(product.calculatePrice(number));

        Optional<ProductInCheck> any1 = check.getProducts().stream().filter(s -> s.getProduct().equals(product)).findAny();

        if (any1.isPresent()) {
            ProductInCheck check = any1.get();
            check.setValue(check.getValue().doubleValue() + productInCheck.getValue().doubleValue());
            check.setPrice(check.getPrice().add(productInCheck.getPrice()));
        } else {
            check.getProducts().add(productInCheck);
        }

        check.setTotalPrice(check.getTotalPrice().add(product.calculatePrice(number)));

        return "redirect:/check";
    }

    @GetMapping("/get_checks")
    public String getAllChecks(Model model) {
        model.addAttribute("checks", checkService.getAllChecks());
        return "checks";
    }
}
