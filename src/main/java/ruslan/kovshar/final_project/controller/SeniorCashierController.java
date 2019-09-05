package ruslan.kovshar.final_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ruslan.kovshar.final_project.entity.User;

@Controller
public class SeniorCashierController {

    @GetMapping("/senior_cashier_menu")
    public String getSeniorCashierMenu() {
        return "senior_cashier";
    }

    @GetMapping("/checks")
    public String getChecksPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("id", user.getId());
        return "checks";
    }
}
