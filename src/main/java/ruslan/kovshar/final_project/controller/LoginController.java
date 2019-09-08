package ruslan.kovshar.final_project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @GetMapping
    public String getLoginPage(@RequestParam(name = "error", required = false) String error,
                               @RequestParam(name = "logout", required = false) String logout,
                               Model model) {
        model.addAttribute("error", error != null);
        if (error != null) {
            log.error("Bad credentials");
        }
        model.addAttribute("logout", logout != null);
        return "login";
    }

}
