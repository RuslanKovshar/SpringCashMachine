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
import ruslan.kovshar.final_project.textcontants.Pages;
import ruslan.kovshar.final_project.textcontants.Params;
import ruslan.kovshar.final_project.textcontants.TextConstants;
import ruslan.kovshar.final_project.textcontants.URIs;

import javax.validation.Valid;
import java.util.Map;

/**
 * controls registration of new user
 */
@Controller
@RequestMapping(URIs.REGISTRATION)
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * displays registration page
     *
     * @param success arises if new user created
     * @param model   model
     * @return page template
     */
    @GetMapping
    public String getRegistrationPage(@RequestParam(name = Params.SUCCESS, required = false) String success, Model model) {
        model.addAttribute(Params.SUCCESS, success != null);
        return Pages.REGISTRATION_PAGE;
    }

    /**
     * registers a new user
     *
     * @param createUserDTO new user data
     * @param bindingResult binding result
     * @param model         model
     * @return redirect to registration page with success if new user created,
     * if some errors arises returns registration page
     */
    @PostMapping
    public String registrateNewUser(@Valid CreateUserDTO createUserDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute(TextConstants.USER_DTO, createUserDTO);
            return Pages.REGISTRATION_PAGE;
        } else {
            if (userService.saveNewUser(userService.createUser(createUserDTO))) {
                return URIs.REDIRECT + URIs.REGISTRATION + Params.PARAM + Params.SUCCESS;
            } else {
                model.addAttribute(TextConstants.USER_DTO, createUserDTO);
                model.addAttribute(TextConstants.EXIST, true);
                return Pages.REGISTRATION_PAGE;
            }
        }
    }
}
