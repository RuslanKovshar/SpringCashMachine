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
import ruslan.kovshar.final_project.exceptions.ResourseNotFoundException;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.service.ProductService;
import ruslan.kovshar.final_project.service.StockService;
import ruslan.kovshar.final_project.view.Pages;
import ruslan.kovshar.final_project.view.Params;
import ruslan.kovshar.final_project.view.TextConstants;
import ruslan.kovshar.final_project.view.URIs;

import javax.validation.Valid;
import java.util.Map;

import static ruslan.kovshar.final_project.view.ExceptionsMessages.TRANSACTION_ERROR;

/**
 * controls all merchandiser functions
 */
@Controller
@RequestMapping(URIs.MERCHANDISER)
public class MerchandiserController {

    private static final Logger log = Logger.getLogger(MerchandiserController.class);

    private final ProductService productService;
    private final StockService stockService;

    public MerchandiserController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    /**
     * displays the merchandiser page
     *
     * @param notFound arises if the product was not found
     * @param model    model
     * @return page template
     */
    @GetMapping
    public String merchandiserPage(@RequestParam(name = Params.NOT_FOUND, required = false) String notFound,
                                   Model model) {
        model.addAttribute(Params.NOT_FOUND, notFound != null);
        return Pages.MERCHANDISER_PAGE;
    }


    /**
     * @param createProductDTO product info
     * @param bindingResult    binding result
     * @param model            model
     * @return redirect to merchandiser page if product created, if not - merchandiser page
     */
    @PostMapping
    public String createProduct(@Valid CreateProductDTO createProductDTO,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute(TextConstants.CREATE_PRODUCT_DTO, createProductDTO);
            return Pages.MERCHANDISER_PAGE;
        }

        Product product;
        if (createProductDTO.getType().equals(Types.PIECE_PRODUCT)) {
            product = new CountProduct(createProductDTO.getCode(),
                    createProductDTO.getName(),
                    createProductDTO.getPrice(),
                    createProductDTO.getType());
        } else {
            product = new WeightProduct(createProductDTO.getCode(),
                    createProductDTO.getName(),
                    createProductDTO.getPrice(),
                    createProductDTO.getType());
        }

        if (!productService.create(product)) {
            model.addAttribute(TextConstants.CREATE_PRODUCT_DTO, createProductDTO);
            model.addAttribute(Params.ERROR,true);
            return Pages.MERCHANDISER_PAGE;
        }

        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setCountOfProduct(createProductDTO.getCount());
        stockService.create(stock);
        return URIs.REDIRECT + URIs.MERCHANDISER;
    }

    /**
     * adds products to the stock
     *
     * @param name           name of product(also can be a code of product)
     * @param countOfProduct count of product
     * @return redirect to merchandiser page
     */
    @PostMapping(URIs.STOCK)
    public String addCount(String name, Integer countOfProduct) {
        Integer code = null;
        try {
            code = Integer.parseInt(name);
        } catch (NumberFormatException ignored) {
        }

        try {
            Product product = productService.loadByCodeOrName(code, name);
            stockService.update(product, countOfProduct);
            return URIs.REDIRECT + URIs.MERCHANDISER;
        } catch (ResourseNotFoundException e) {
            log.error(e.getMessage());
            return URIs.REDIRECT + URIs.MERCHANDISER + Params.PARAM + Params.NOT_FOUND;
        } catch (TransactionException e) {
            log.error(TRANSACTION_ERROR);
            return URIs.REDIRECT + URIs.MERCHANDISER;
        }
    }
}
