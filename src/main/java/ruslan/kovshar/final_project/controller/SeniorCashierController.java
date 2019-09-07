package ruslan.kovshar.final_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.service.CheckService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/senior_cashier")
public class SeniorCashierController {

    private final CheckService checkService;

    public SeniorCashierController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/checks")
    public String getChecksPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("id", user.getId());
        return "checks";
    }

    @GetMapping("/x-report")
    public String getXReport(@AuthenticationPrincipal User user, Model model) {
  //      model.addAttribute("reportType", "x-report");
//        List<Check> userCheck = checkService.getAllChecksByUser(user);
        Set<Check> checks = user.getChecks();

        model.addAttribute("countOfChecks",checks.size());

        double sum = checks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();
        model.addAttribute("totalSum",sum);
        model.addAttribute("id",user.getId());
        return "x-report";
    }

    @GetMapping("/z-report")
    public String getZReport(@AuthenticationPrincipal User user, Model model) {
        //      model.addAttribute("reportType", "x-report");
//        List<Check> userCheck = checkService.getAllChecksByUser(user);
        Set<Check> checks = user.getChecks();

        model.addAttribute("countOfChecks",checks.size());

        double sum = checks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();
        model.addAttribute("totalSum",sum);
        model.addAttribute("id",user.getId());
        return "report";
    }
}
