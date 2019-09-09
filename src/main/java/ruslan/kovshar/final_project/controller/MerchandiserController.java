package ruslan.kovshar.final_project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import javax.validation.Valid;
import java.util.Map;

import static ruslan.kovshar.final_project.view.ExceptionsMessages.TRANSACTION_ERROR;
import static ruslan.kovshar.final_project.view.Pages.MERCHANDISER_PAGE;
import static ruslan.kovshar.final_project.view.RequestParams.NOT_FOUND;
import static ruslan.kovshar.final_project.view.RequestParams.PARAM;
import static ruslan.kovshar.final_project.view.TextConstants.CREATE_PRODUCT_DTO;
import static ruslan.kovshar.final_project.view.URIs.*;

@Controller
@RequestMapping(MERCHANDISER)
public class MerchandiserController {

    private static final Logger log = Logger.getLogger(MerchandiserController.class);

    private final ProductService productService;
    private final StockService stockService;

    public MerchandiserController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    @GetMapping
    public String getMerchandiserPage(@RequestParam(name = NOT_FOUND, required = false) String notFound,
                                      Model model) {
        model.addAttribute(NOT_FOUND, notFound != null);
        return MERCHANDISER_PAGE;
    }

    @PostMapping
    public String createProduct(@Valid CreateProductDTO createProductDTO,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute(CREATE_PRODUCT_DTO, createProductDTO);
            return MERCHANDISER_PAGE;
        }

        Product product;
        if (createProductDTO.getType().equals(Types.PIECE_PRODUCT)) {
            product = new CountProduct(createProductDTO.getCode(),
                    createProductDTO.getNameUA(),
                    createProductDTO.getNameEN(),
                    createProductDTO.getPrice(),
                    createProductDTO.getType());
        } else {
            product = new WeightProduct(createProductDTO.getCode(),
                    createProductDTO.getNameUA(),
                    createProductDTO.getNameEN(),
                    createProductDTO.getPrice(),
                    createProductDTO.getType());
        }
        productService.create(product);

        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setCountOfProduct(createProductDTO.getCount());
        stockService.create(stock);
        return REDIRECT + MERCHANDISER;
    }

    @PostMapping(STOCK)
    public String addCount(String name, Integer countOfProduct) {
        Integer code = null;
        try {
            code = Integer.parseInt(name);
        } catch (NumberFormatException ignored) {
        }

        try {
            Product product = productService.loadByCodeOrName(code, name);
            stockService.update(product, countOfProduct);
            return REDIRECT + MERCHANDISER;
        } catch (ProductNotFoundException e) {
            log.error(e.getMessage());
            return REDIRECT + MERCHANDISER + PARAM + NOT_FOUND;
        } catch (TransactionException e) {
            log.error(TRANSACTION_ERROR);
            return REDIRECT + MERCHANDISER;
        }
    }
}
