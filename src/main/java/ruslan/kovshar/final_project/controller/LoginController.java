package ruslan.kovshar.final_project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruslan.kovshar.final_project.textcontants.Pages;
import ruslan.kovshar.final_project.textcontants.Params;
import ruslan.kovshar.final_project.textcontants.URIs;

import static ruslan.kovshar.final_project.textcontants.ExceptionsMessages.BAG_CREDENTIALS;


/**
 * controls login
 */
@Controller
@RequestMapping(URIs.LOGIN)
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    /**
     * displays login page
     *
     * @param error  arises with wrong user data
     * @param logout arises when user logged out
     * @param model  model
     * @return page template
     */
    @GetMapping
    public String loginPage(@RequestParam(name = Params.ERROR, required = false) String error,
                            @RequestParam(name = Params.LOGOUT, required = false) String logout,
                            Model model) {
        model.addAttribute(Params.ERROR, error != null);
        if (error != null) {
            log.error(BAG_CREDENTIALS);
        }
        model.addAttribute(Params.LOGOUT, logout != null);
        return Pages.LOGIN_PAGE;
    }

}
