package ruslan.kovshar.final_project.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.dto.GetProductDTO;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.ProductInCheck;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.exceptions.ProductNotFoundException;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.service.CheckService;
import ruslan.kovshar.final_project.service.ProductService;
import ruslan.kovshar.final_project.service.StockService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ruslan.kovshar.final_project.view.ExceptionsMessages.TRANSACTION_ERROR;
import static ruslan.kovshar.final_project.view.Pages.CHECK_PAGE;
import static ruslan.kovshar.final_project.view.Pages.PRODUCT_PAGE;
import static ruslan.kovshar.final_project.view.RequestParams.*;
import static ruslan.kovshar.final_project.view.TextConstants.CHECK_PARAM;
import static ruslan.kovshar.final_project.view.TextConstants.PRODUCT_PARAM;
import static ruslan.kovshar.final_project.view.URIs.*;

@Controller
public class ProductsController {

    private static final Logger log = Logger.getLogger(ProductsController.class);

    private final ProductService productService;
    private final CheckService checkService;
    private final StockService stockService;
    private Check check;

    public ProductsController(ProductService productService, CheckService checkService, StockService stockService) {
        this.productService = productService;
        this.checkService = checkService;
        this.stockService = stockService;
    }

    @GetMapping(CHECK)
    public String getCheckPage(@RequestParam(name = NOT_FOUND, required = false) String notFound,
                               Model model) {
        if (check != null) {
            model.addAttribute(CHECK_PARAM, check);
        }
        model.addAttribute(NOT_FOUND, notFound != null);
        return CHECK_PAGE;
    }

    @GetMapping(CHECK + PRODUCT + ID)
    public String getProductPage(@PathVariable(name = ID_PARAM, required = false) Product product,
                                 @RequestParam(name = ERROR, required = false) String error,
                                 Model model,
                                 SessionLocaleResolver resolver,
                                 HttpServletRequest req) {

        model.addAttribute(PRODUCT_PARAM, new GetProductDTO(product, resolver, req));
        model.addAttribute(ERROR, error != null);
        return PRODUCT_PAGE;
    }

    @PostMapping(CHECK + PRODUCT)
    public String findProduct(String name) {
        Integer code = null;
        try {
            code = Integer.parseInt(name);
        } catch (NumberFormatException ignored) {
        }

        try {
            Product product = productService.loadByCodeOrName(code, name);
            return REDIRECT + CHECK + PRODUCT + SLASH + product.getId();
        } catch (ProductNotFoundException e) {
            log.error(e.getMessage());
            return REDIRECT + CHECK + PARAM + NOT_FOUND;
        }
    }

    @GetMapping(OPEN_CHECK)
    public String openCheck() {
        if (check == null) {
            check = new Check();
        }
        return REDIRECT + CHECK;
    }

    @PostMapping(CLOSE_CHECK)
    public String closeCheck(@AuthenticationPrincipal User user) {
        check.setUser(user);
        user.getChecks().add(check);
        checkService.saveCheck(check);
        check = null;
        return REDIRECT + HOME;
    }

    @PostMapping(PRODUCT + ADD + ID)
    public String addProductToCheck(@PathVariable(name = ID_PARAM) Product product,
                                    Number number) {
        try {
            stockService.update(product, -number.intValue());
        } catch (TransactionException e) {
            log.error(TRANSACTION_ERROR);
            return REDIRECT + CHECK + PRODUCT + SLASH + product.getId() + PARAM + ERROR;
        }

        Optional<ProductInCheck> productInCheck = check.getProducts().stream().filter(s -> s.getProduct().equals(product)).findAny();

        if (productInCheck.isPresent()) {
            ProductInCheck check = productInCheck.get();
            check.setValue(check.getValue().doubleValue() + number.doubleValue());
            check.setPrice(check.getPrice().add(product.calculatePrice(number)));
        } else {
            ProductInCheck newProduct = new ProductInCheck();
            newProduct.setProduct(product);
            if (product.getClass().equals(ruslan.kovshar.final_project.entity.CountProduct.class)) {
                newProduct.setValue(number.intValue());
            } else {
                newProduct.setValue(number.doubleValue());
            }
            newProduct.setPrice(product.calculatePrice(number));
            check.getProducts().add(newProduct);
        }

        check.setTotalPrice(check.getTotalPrice().add(product.calculatePrice(number)));
        return REDIRECT + CHECK;
    }
}
