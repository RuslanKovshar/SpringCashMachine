package ruslan.kovshar.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.dto.GetUserDTO;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.entity.UserRole;
import ruslan.kovshar.final_project.enums.Roles;
import ruslan.kovshar.final_project.repository.UserRoleRepository;
import ruslan.kovshar.final_project.service.UserService;
import ruslan.kovshar.final_project.textcontants.Pages;
import ruslan.kovshar.final_project.textcontants.TextConstants;
import ruslan.kovshar.final_project.textcontants.URIs;
import ruslan.kovshar.final_project.textcontants.Params;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * controls admin functions
 */
@Controller
@RequestMapping(URIs.ADMIN)
public class AdminController {

    private final UserService userService;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public AdminController(UserService userService, UserRoleRepository userRoleRepository) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
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
        user.getAuthorities().clear();

        Set<UserRole> newUserRoles = Arrays.stream(roles).map(Roles::valueOf).map(role -> {
            Optional<UserRole> userRoleOptional = userRoleRepository.findByRole(role);
            if (userRoleOptional.isPresent()) {
                return userRoleOptional.get();
            }
            throw new IllegalArgumentException("No such UserRole: " + role);
        }).collect(Collectors.toSet());

        user.setAuthorities(newUserRoles);
        userService.updateUser(user);
        return URIs.REDIRECT + URIs.ADMIN + URIs.USERS;
    }
}
