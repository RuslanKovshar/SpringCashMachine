package ruslan.kovshar.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruslan.kovshar.final_project.dto.CreateUserDTO;
import ruslan.kovshar.final_project.service.UserService;

import javax.validation.Valid;
import java.util.Map;

import static ruslan.kovshar.final_project.view.Pages.REGISTRATION_PAGE;
import static ruslan.kovshar.final_project.view.RequestParams.PARAM;
import static ruslan.kovshar.final_project.view.RequestParams.SUCCESS;
import static ruslan.kovshar.final_project.view.TextConstants.USER_DTO;
import static ruslan.kovshar.final_project.view.URIs.REDIRECT;
import static ruslan.kovshar.final_project.view.URIs.REGISTRATION;

@Controller
@RequestMapping(REGISTRATION)
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegistrationPage(@RequestParam(name = SUCCESS, required = false) String success, Model model) {
        model.addAttribute(SUCCESS, success != null);
        return REGISTRATION_PAGE;
    }

    @PostMapping
    public String registrateNewUser(@Valid CreateUserDTO createUserDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute(USER_DTO, createUserDTO);
            return REGISTRATION_PAGE;
        } else {
            if (userService.saveNewUser(createUserDTO)) {
                return REDIRECT + REGISTRATION + PARAM + SUCCESS;
            } else {
                model.addAttribute(USER_DTO, createUserDTO);
                return REGISTRATION_PAGE;
            }
        }
    }
}
