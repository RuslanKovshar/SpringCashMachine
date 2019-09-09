package ruslan.kovshar.final_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;

import java.util.Set;

import static ruslan.kovshar.final_project.view.Pages.X_REPORT_PAGE;
import static ruslan.kovshar.final_project.view.Pages.Z_REPORT_PAGE;
import static ruslan.kovshar.final_project.view.TextConstants.*;
import static ruslan.kovshar.final_project.view.URIs.*;

@Controller
@RequestMapping(SENIOR_CASHIER)
public class SeniorCashierController {

    @GetMapping(X_REPORT)
    public String getXReport(@AuthenticationPrincipal User user, Model model) {
        makeReport(user, model);
        return X_REPORT_PAGE;
    }

    @GetMapping(Z_REPORT)
    public String getZReport(@AuthenticationPrincipal User user, Model model) {
        makeReport(user, model);
        return Z_REPORT_PAGE;
    }

    private void makeReport(User user, Model model) {
        Set<Check> checks = user.getChecks();
        model.addAttribute(COUNT_OF_CHECKS, checks.size());
        double sum = checks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();
        model.addAttribute(TOTAL_SUM, sum);
        model.addAttribute(ENTITY_ID, user.getId());
    }
}
