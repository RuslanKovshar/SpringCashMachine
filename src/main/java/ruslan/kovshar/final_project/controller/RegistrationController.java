package ruslan.kovshar.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruslan.kovshar.final_project.dto.UserDTO;
import ruslan.kovshar.final_project.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegistrationPage(@RequestParam(name = "success", required = false) String success, Model model) {
        model.addAttribute("success", success != null);
        return "registration";
    }

    @PostMapping
    public String registrateNewUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("userDTO", userDTO);
            return "registration";
        } else {
            if (userService.saveNewUser(userDTO)) {
                return "redirect:/registration?success";
            } else {
                model.addAttribute("userDTO", userDTO);
                return "registration";
            }
        }
    }
}
