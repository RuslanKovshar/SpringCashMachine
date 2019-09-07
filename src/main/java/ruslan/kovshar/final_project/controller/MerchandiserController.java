package ruslan.kovshar.final_project.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruslan.kovshar.final_project.dto.CreateProductDTO;
import ruslan.kovshar.final_project.entity.CountProduct;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.Stock;
import ruslan.kovshar.final_project.entity.WeightProduct;
import ruslan.kovshar.final_project.enums.Types;
import ruslan.kovshar.final_project.exceptions.ProductNotFoundException;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.service.ProductService;
import ruslan.kovshar.final_project.service.StockService;

@Controller
@PreAuthorize("hasAuthority('MERCHANDISER')")
@RequestMapping("/merchandiser")
public class MerchandiserController {

    private final ProductService productService;
    private final StockService stockService;

    public MerchandiserController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    @GetMapping
    public String getMerchandiserPage(@RequestParam(name = "notFound", required = false) String notFound,
                                      Model model) {
        model.addAttribute("notFound", notFound != null);
        return "merchandiser";
    }

    @PostMapping
    public String createProduct(CreateProductDTO createProductDTO, Integer count) {
        Product product;
        if (createProductDTO.getType().equals(Types.PIECE_PRODUCT)) {
            product = new CountProduct(createProductDTO.getCode(),
                    createProductDTO.getNameUA(),
                    createProductDTO.getNameEN(),
                    createProductDTO.getPrice());
        } else {
            product = new WeightProduct(createProductDTO.getCode(),
                    createProductDTO.getNameUA(),
                    createProductDTO.getNameEN(),
                    createProductDTO.getPrice());
        }
        productService.create(product);

        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setCountOfProduct(count);
        stockService.create(stock);
        return "redirect:/merchandiser";
    }

    @PostMapping("/stock")
    public String addCount(String name, Integer countOfProduct) {
        Integer code = null;
        try {
            code = Integer.parseInt(name);
        } catch (NumberFormatException ignored) {
        }

        try {
            Product product = productService.loadByCodeOrName(code, name);
            stockService.update(product, countOfProduct);
            return "redirect:/merchandiser";
        } catch (ProductNotFoundException e) {
            return "redirect:/merchandiser?notFound";
        } catch (TransactionException e) {
            e.printStackTrace();
            //TODO: fix this
            return "redirect:/merchandiser?notFound";
        }
    }
}
