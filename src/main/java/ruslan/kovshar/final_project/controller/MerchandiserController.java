package ruslan.kovshar.final_project.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruslan.kovshar.final_project.dto.ProductDTO;
import ruslan.kovshar.final_project.entity.CountProduct;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.Stock;
import ruslan.kovshar.final_project.entity.WeightProduct;
import ruslan.kovshar.final_project.enums.Types;
import ruslan.kovshar.final_project.exceptions.ProductNotFoundException;
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
    public String getMerchandiserPage() {
        return "merchandiser";
    }

    @PostMapping
    public String createProduct(ProductDTO productDTO, Integer count) {
        Product product;
        if (productDTO.getType().equals(Types.PIECE_PRODUCT)) {
            product = new CountProduct(productDTO.getCode(),
                                        productDTO.getName(),
                                        productDTO.getPrice());
        } else {
            product = new WeightProduct(productDTO.getCode(),
                                        productDTO.getName(),
                                        productDTO.getPrice());
        }
        productService.create(product);

        Stock stock = new Stock();
        stock.setProductId(product.getId());
        stock.setCountOfProduct(count);
        stockService.create(stock);

        return "redirect:/merchandiser";
    }

    @PostMapping("/stock")
    public String addCount(String name, Integer countOfProduct, Model model) {
        try {
            Product product = productService.loadByName(name);
            stockService.update(product.getId(), countOfProduct);
        } catch (ProductNotFoundException e) {
            System.err.println("Not found");
            model.addAttribute("notFound",true);
            return "merchandiser";
        }
        return "redirect:/merchandiser";
    }
}
