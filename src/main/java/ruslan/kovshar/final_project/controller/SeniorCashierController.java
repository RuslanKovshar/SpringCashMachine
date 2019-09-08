package ruslan.kovshar.final_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;

import java.util.Set;

@Controller
@RequestMapping("/senior_cashier")
public class SeniorCashierController {

    @GetMapping("/x-report")
    public String getXReport(@AuthenticationPrincipal User user, Model model) {
        makeReport(user, model);
        return "x-report";
    }

    @GetMapping("/z-report")
    public String getZReport(@AuthenticationPrincipal User user, Model model) {
        makeReport(user, model);
        return "z_report";
    }

    private void makeReport(User user, Model model) {
        Set<Check> checks = user.getChecks();
        model.addAttribute("countOfChecks", checks.size());
        double sum = checks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();
        model.addAttribute("totalSum", sum);
        model.addAttribute("id", user.getId());
    }
}
