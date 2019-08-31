package ruslan.kovshar.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.exceptions.ProductNotFoundException;
import ruslan.kovshar.final_project.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ProductsController {

    private final ProductService productService;
    private Check check;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String getProductPage(Model model) {
        if (check != null) {
            model.addAttribute("check", check);
        }
        return "product";
    }

    @PostMapping("/product")
    public String addProductToCheck(String name, Model model) {
        Integer code = null;
        try {
            code = Integer.parseInt(name);
        } catch (NumberFormatException ignored) {
        }

        try {
            Product product = productService.loadByCodeOrName(code, name);
            model.addAttribute("product", product);
        } catch (ProductNotFoundException e) {
            System.err.println("not found");
        }
        return "product";
    }

    @GetMapping("/open_check")
    public String openCheck() {
        check = new Check();
        return "redirect:/product";
    }

    @PostMapping("/close_check")
    public String closeCheck(HttpSession session) {
        if (session.getAttribute("checkList") == null) {
            session.setAttribute("checkList", new ArrayList<Check>());
        }


        return "redirect:/";
    }


    @PostMapping("/product/add/{id}")
    public String addProduct(@PathVariable(name = "id") Product product, Number number) {
        System.out.println(check);
        System.err.println(number);
        check.addProduct(product, number);
        return "redirect:/product";
    }
}
