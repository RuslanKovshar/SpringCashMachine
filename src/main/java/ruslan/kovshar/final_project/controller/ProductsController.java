package ruslan.kovshar.final_project.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruslan.kovshar.final_project.dto.GetProductDTO;
import ruslan.kovshar.final_project.entity.*;
import ruslan.kovshar.final_project.exceptions.ResourseNotFoundException;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.service.CheckService;
import ruslan.kovshar.final_project.service.PaymentService;
import ruslan.kovshar.final_project.service.ProductService;
import ruslan.kovshar.final_project.service.StockService;
import ruslan.kovshar.final_project.view.Pages;
import ruslan.kovshar.final_project.view.Params;
import ruslan.kovshar.final_project.view.TextConstants;
import ruslan.kovshar.final_project.view.URIs;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

import static ruslan.kovshar.final_project.view.ExceptionsMessages.TRANSACTION_ERROR;

/**
 * Class that controls all checks and products operations
 */
@Controller
public class ProductsController {

    private static final Logger log = Logger.getLogger(ProductsController.class);

    private final ProductService productService;
    private final CheckService checkService;
    private final StockService stockService;
    private final PaymentService paymentService;

    public ProductsController(ProductService productService, CheckService checkService, StockService stockService, PaymentService paymentService) {
        this.productService = productService;
        this.checkService = checkService;
        this.stockService = stockService;
        this.paymentService = paymentService;
    }

    /**
     * displays check page
     *
     * @param notFound arises if the product was not found
     * @param model    model
     * @param session  http session
     * @return page template
     */
    @GetMapping(URIs.CHECK)
    public String checkPage(@RequestParam(name = Params.NOT_FOUND, required = false) String notFound,
                            Model model,
                            HttpSession session) {
        Check check = (Check) session.getAttribute(Params.CHECK);
        if (check != null) {
            model.addAttribute(TextConstants.CHECK_PARAM, check);
        }
        model.addAttribute(Params.NOT_FOUND, notFound != null);
        return Pages.CHECK_PAGE;
    }

    /**
     * displays product page
     *
     * @param error   arises if count of the product is not enough
     * @param model   model
     * @param session http session
     * @return page template
     */
    @GetMapping(URIs.CHECK + URIs.PRODUCT)
    public String productPage(@RequestParam(name = Params.ERROR, required = false) String error,
                              Model model,
                              HttpSession session) {
        Product product = (Product) session.getAttribute(Params.PRODUCT);
        model.addAttribute(TextConstants.PRODUCT_PARAM, new GetProductDTO(product));
        model.addAttribute(Params.ERROR, error != null);
        return Pages.PRODUCT_PAGE;
    }

    /**
     * finds product by name or code
     *
     * @param name    name of product(also can be a code of product)
     * @param session session
     * @return product page if product exists, and check page with notFound param if not
     */
    @PostMapping(URIs.CHECK + URIs.PRODUCT)
    public String findProduct(String name, HttpSession session) {
        Integer code = null;
        try {
            code = Integer.parseInt(name);
        } catch (NumberFormatException ignored) {
        }

        try {
            Product product = productService.loadByCodeOrName(code, name);
            session.setAttribute(Params.PRODUCT, product);
            return URIs.REDIRECT + URIs.CHECK + URIs.PRODUCT;
        } catch (ResourseNotFoundException e) {
            log.error(e.getMessage());
            return URIs.REDIRECT + URIs.CHECK + Params.PARAM + Params.NOT_FOUND;
        }
    }

    /**
     * opens the check if it does not open
     *
     * @param session http session
     * @return redirect to check page
     */
    @GetMapping(URIs.OPEN_CHECK)
    public String openCheck(HttpSession session) {
        if (session.getAttribute(Params.CHECK) == null) {
            session.setAttribute(Params.CHECK, new Check());
        }
        return URIs.REDIRECT + URIs.CHECK;
    }

    /**
     * gets check total price and redirect to payment
     *
     * @param session http session
     * @return redirect to payment page
     */
    @PostMapping(URIs.CLOSE_CHECK)
    public String closeCheck(HttpSession session) {
        Check check = (Check) session.getAttribute(Params.CHECK);
        return URIs.REDIRECT + URIs.PAYMENT + Params.PARAM + Params.VALUE + "=" + check.getTotalPrice();
    }

    /**
     * makes payment from buyer to user
     *
     * @param user    user
     * @param buyer   buyer info
     * @param value   price that buyer has to pay
     * @param session http session
     * @return redirect to home page
     */
    @PostMapping(URIs.PAYMENT)
    public String makePayment(@AuthenticationPrincipal User user, Buyer buyer, BigDecimal value, HttpSession session) {
        Check check = (Check) session.getAttribute(Params.CHECK);
        user.setCash(user.getCash().add(value));
        paymentService.makePay(user, buyer);
        check.setUser(user);
        check.setBuyer(buyer);
        user.getChecks().add(check);
        checkService.saveCheck(check);
        session.removeAttribute(Params.CHECK);
        return URIs.REDIRECT + URIs.HOME;
    }

    /**
     * displays payment page
     *
     * @param value price that buyer has to pay
     * @param model model
     * @return page template
     */
    @GetMapping(URIs.PAYMENT)
    public String paymentPage(@RequestParam(name = Params.VALUE) BigDecimal value, Model model) {
        model.addAttribute(Params.VALUE, value);
        return Pages.PAYMENT_PAGE;
    }


    /**
     * adds product to check.
     * if product already in check add count of the product
     *
     * @param countOfProduct count of product
     * @param session        http session
     * @return redirect to check page if enough product in stock,
     * redirect to product page with error
     */
    @PostMapping(URIs.PRODUCT + URIs.ADD)
    public String addProductToCheck(Integer countOfProduct, HttpSession session) {
        Product product = (Product) session.getAttribute(Params.PRODUCT);
        try {
            stockService.update(product, -countOfProduct);
        } catch (TransactionException e) {
            log.error(TRANSACTION_ERROR);
            return URIs.REDIRECT + URIs.CHECK + URIs.PRODUCT + Params.PARAM + Params.ERROR;
        }

        Check check = (Check) session.getAttribute(Params.CHECK);
        Optional<ProductInCheck> productInCheck = check.getProducts().stream().filter(s -> s.getProduct().equals(product)).findAny();

        if (productInCheck.isPresent()) {
            ProductInCheck inCheck = productInCheck.get();
            inCheck.setCountOfProduct(inCheck.getCountOfProduct() + countOfProduct);
            inCheck.setPrice(inCheck.getPrice().add(product.calculatePrice(countOfProduct)));
        } else {
            ProductInCheck newProduct = new ProductInCheck();
            newProduct.setProduct(product);
            newProduct.setCountOfProduct(countOfProduct);
            newProduct.setPrice(product.calculatePrice(countOfProduct));
            newProduct.setCheck(check);
            check.getProducts().add(newProduct);
        }
        check.calculateTotalPrice();
        session.removeAttribute(Params.PRODUCT);
        return URIs.REDIRECT + URIs.CHECK;
    }
}
