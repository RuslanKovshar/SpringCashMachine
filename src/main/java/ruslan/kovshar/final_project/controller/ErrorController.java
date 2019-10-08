package ruslan.kovshar.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruslan.kovshar.final_project.textcontants.Pages;
import ruslan.kovshar.final_project.textcontants.URIs;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping(URIs.FORBIDDEN)
    public String page403() {
        return Pages.FORBIDDEN;
    }

    @RequestMapping(URIs.ERROR)
    public String handleResourceNotFoundException() {
        return Pages.NOT_FOUND;
    }

    @Override
    public String getErrorPath() {
        return URIs.ERROR;
    }
}
