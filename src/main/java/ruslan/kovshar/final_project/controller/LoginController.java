package ruslan.kovshar.final_project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static ruslan.kovshar.final_project.view.ExceptionsMessages.BAG_CREDENTIALS;
import static ruslan.kovshar.final_project.view.Pages.LOGIN_PAGE;
import static ruslan.kovshar.final_project.view.RequestParams.ERROR;
import static ruslan.kovshar.final_project.view.RequestParams.LOGOUT;
import static ruslan.kovshar.final_project.view.URIs.LOGIN;

@Controller
@RequestMapping(LOGIN)
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @GetMapping
    public String getLoginPage(@RequestParam(name = ERROR, required = false) String error,
                               @RequestParam(name = LOGOUT, required = false) String logout,
                               Model model) {
        model.addAttribute(ERROR, error != null);
        if (error != null) {
            log.error(BAG_CREDENTIALS);
        }
        model.addAttribute(LOGOUT, logout != null);
        return LOGIN_PAGE;
    }

}
