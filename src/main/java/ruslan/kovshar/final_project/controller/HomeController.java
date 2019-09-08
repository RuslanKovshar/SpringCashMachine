package ruslan.kovshar.final_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.dto.GetUserDTO;
import ruslan.kovshar.final_project.entity.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHomePage(@AuthenticationPrincipal User user, Model model,HttpServletRequest request,SessionLocaleResolver localeResolver) {
        model.addAttribute("userDTO",new GetUserDTO(user,localeResolver,request));
        return "home";
    }
}
