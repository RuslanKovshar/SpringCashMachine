package ruslan.kovshar.final_project.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.service.CheckService;

@RestController
public class MainController {

    private final CheckService checkService;

    public MainController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/senior_cashier_menu/cashier/{id}/checks")
    public Page<Check> getAll(@PathVariable(name = "id") User user,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 6) Pageable pageable) {

        return checkService.getAllChecksByUser(user, pageable);
    }
}
