package ruslan.kovshar.final_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import ruslan.kovshar.final_project.textcontants.Pages;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping("/forbidden")
    public String page403() {
        return Pages.FORBIDDEN;
    }

    @RequestMapping("/error")
    public String handleResourceNotFoundException() {
        return Pages.NOT_FOUND;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
