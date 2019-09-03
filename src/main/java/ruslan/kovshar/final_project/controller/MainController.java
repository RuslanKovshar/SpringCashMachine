package ruslan.kovshar.final_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.enums.Roles;
import ruslan.kovshar.final_project.service.CheckService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
public class MainController {

    private final CheckService checkService;

    public MainController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/dai")
    public List<Check> get() {
        return checkService.getAllChecks();
    }

    @GetMapping("/user_checks")
    public Set<Check> getAll(@AuthenticationPrincipal User user){
        System.err.println(user.getChecks());
        return user.getChecks();
    }
}
