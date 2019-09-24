package ruslan.kovshar.final_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.dto.GetUserDTO;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.view.Pages;
import ruslan.kovshar.final_project.view.TextConstants;
import ruslan.kovshar.final_project.view.URIs;

import javax.servlet.http.HttpServletRequest;


/**
 * controls home page
 */
@Controller
@RequestMapping(URIs.HOME)
public class HomeController {

    /**
     * displays home page
     *
     * @param user           user
     * @param model          model
     * @param request        http request
     * @param localeResolver locale resolver
     * @return page template
     */
    @GetMapping
    public String homePage(@AuthenticationPrincipal User user,
                           Model model,
                           HttpServletRequest request,
                           SessionLocaleResolver localeResolver) {
        model.addAttribute(TextConstants.USER_DTO, new GetUserDTO(user, localeResolver, request));
        return Pages.HOME_PAGE;
    }
}
