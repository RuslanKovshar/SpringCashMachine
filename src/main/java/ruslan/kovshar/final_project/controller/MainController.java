package ruslan.kovshar.final_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final CheckHolder checkHolder = CheckHolder.getInstance();

    @GetMapping("/get_products")
    public String getProducts() {
        System.err.println(checkHolder);
        return null;
    }
}
