package ruslan.kovshar.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.dto.GetUserDTO;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.enums.Roles;
import ruslan.kovshar.final_project.service.UserService;
import ruslan.kovshar.final_project.textcontants.Pages;
import ruslan.kovshar.final_project.textcontants.TextConstants;
import ruslan.kovshar.final_project.textcontants.URIs;
import ruslan.kovshar.final_project.textcontants.Params;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * controls admin functions
 */
@Controller
@RequestMapping(URIs.ADMIN)
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * displays page with all users
     *
     * @param resolver session locale resolver
     * @param req      http servlet request
     * @param model    model
     * @return page template
     */
    @GetMapping(URIs.USERS)
    public String usersPage(SessionLocaleResolver resolver, HttpServletRequest req, Model model) {
        List<User> allUsers = userService.getAllUsers();
        List<GetUserDTO> users = new ArrayList<>();
        allUsers.forEach(user -> users.add(new GetUserDTO(user, resolver, req)));
        model.addAttribute(TextConstants.USERS, users);
        return Pages.USERS;
    }

    /**
     * displays user editor page
     *
     * @param user     user
     * @param resolver session locale resolver
     * @param req      http servlet request
     * @param model    model
     * @return page template
     */
    @GetMapping(URIs.USER + "/{id}")
    public String editUserPage(@PathVariable(name = Params.ID_PARAM) User user,
                               SessionLocaleResolver resolver,
                               HttpServletRequest req,
                               Model model) {
        GetUserDTO userDTO = new GetUserDTO(user, resolver, req);
        model.addAttribute(TextConstants.USER_DTO, userDTO);
        return Pages.EDIT_USER;
    }

    /**
     * sets new roles for the user
     *
     * @param user  user
     * @param roles roles
     * @return redirect to all users page
     */
    @PostMapping(URIs.USER + "/{id}")
    public String editUser(@PathVariable(name = Params.ID_PARAM) User user, String[] roles) {
        Set<Roles> newRoles = Arrays.stream(roles).map(Roles::valueOf).collect(Collectors.toSet());
        user.setAuthorities(newRoles);
        userService.updateUser(user);
        return URIs.REDIRECT + URIs.ADMIN + URIs.USERS;
    }
}
