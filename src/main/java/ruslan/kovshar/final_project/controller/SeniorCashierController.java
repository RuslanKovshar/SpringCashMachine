package ruslan.kovshar.final_project.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.ProductInCheck;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.service.CheckService;
import ruslan.kovshar.final_project.service.PaymentService;
import ruslan.kovshar.final_project.service.StockService;
import ruslan.kovshar.final_project.view.Pages;
import ruslan.kovshar.final_project.view.Params;
import ruslan.kovshar.final_project.view.URIs;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * controls all senior cashier functions
 */
@Controller
@RequestMapping(URIs.SENIOR_CASHIER)
public class SeniorCashierController {

    private static final Logger log = Logger.getLogger(SeniorCashierController.class);

    private final CheckService checkService;
    private final StockService stockService;
    private final PaymentService paymentService;

    public SeniorCashierController(CheckService checkService, StockService stockService, PaymentService paymentService) {
        this.checkService = checkService;
        this.stockService = stockService;
        this.paymentService = paymentService;
    }

    /**
     * displays x-report page
     *
     * @param user  user
     * @param model model
     * @return page template
     */
    @GetMapping(URIs.X_REPORT)
    public String xReportPage(@AuthenticationPrincipal User user, Model model) {
        makeReport(user, model);
        return Pages.X_REPORT_PAGE;
    }

    /**
     * displays z-report page
     *
     * @param user  user
     * @param model model
     * @return page template
     */
    @GetMapping(URIs.Z_REPORT)
    public String zReportPage(@AuthenticationPrincipal User user, Model model) {
        makeReport(user, model);
        return Pages.Z_REPORT_PAGE;
    }

    /**
     * displays page with all users check
     *
     * @param user  user
     * @param model model
     * @return page template
     */
    @GetMapping(URIs.CHECKS)
    public String checksPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute(Params.ID_PARAM, user.getId());
        model.addAttribute(Params.CHECKS, checkService.getAllChecksByUser(user));
        return Pages.CHECKS_PAGE;
    }

    /**
     * cancels check, returns money to buyer
     *
     * @param user user
     * @param id   check id
     * @return redirect to home page
     */
    @PostMapping(URIs.CANCEL_CHECK)
    public String cancelCheck(@AuthenticationPrincipal User user, @RequestParam(name = Params.ID_PARAM) Long id) {
        Check check = user.getChecks().stream().filter(tempCheck -> tempCheck.getId().equals(id)).findAny().orElseThrow(RuntimeException::new);
        check.getProducts().forEach(productInCheck -> {
            try {
                stockService.update(productInCheck.getProduct(), productInCheck.getCountOfProduct());
            } catch (TransactionException e) {
                e.printStackTrace();
            }
        });
        user.setCash(user.getCash().subtract(check.getTotalPrice()));
        user.getChecks().remove(check);
        paymentService.returnMoney(user);
        checkService.deleteCheck(check);
        return URIs.REDIRECT + Params.SLASH;
    }

    /**
     * removes product from check
     *
     * @param session http session
     * @param name    name of product
     * @return redirect to check page if all is good, redirect to product page with error param if not
     */
    @PostMapping(URIs.CHECK + URIs.REMOVE_PRODUCT)
    public String removeProduct(HttpSession session, String name) {
        Check check = (Check) session.getAttribute(Params.CHECK);

        Optional<ProductInCheck> productInCheck = check.getProducts()
                .stream()
                .filter(s -> s.getProduct().getNameUA().equals(name))
                .findAny();

        if (productInCheck.isPresent()) {
            ProductInCheck checkProduct = productInCheck.get();
            check.getProducts().remove(checkProduct);
            try {
                stockService.update(checkProduct.getProduct(), checkProduct.getCountOfProduct());
            } catch (TransactionException e) {
                log.error(e.getMessage());
                return URIs.REDIRECT + URIs.CHECK + URIs.PRODUCT + Params.PARAM + Params.ERROR;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        check.calculateTotalPrice();
        return URIs.REDIRECT + URIs.CHECK;
    }

    private void makeReport(User user, Model model) {/*
        Set<Check> checks = user.getChecks();
        model.addAttribute(COUNT_OF_CHECKS, checks.size());
        double sum = checks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();
        model.addAttribute(TOTAL_SUM, sum);
        model.addAttribute(ENTITY_ID, user.getId());*/
    }
}
