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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class MainController {

    private final CheckService checkService;

    public MainController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/api/cashier/{id}/order-x")
    public Page<Check> getChecksAfterX(@PathVariable(name = "id") User user,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 6) Pageable pageable) {
        return checkService.getAllChecksByUser(user, pageable);
    }

    @GetMapping("/api/cashier/{id}/order-z")
    public List<Check> getChecksAfterZ(@PathVariable(name = "id") User user,
                                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 6) Pageable pageable) {

        user.setChecks(Collections.emptySet());
        List<Check> checks = new ArrayList<>(checkService.getAllChecksByUser(user));
        checkService.clearChecks(checkService.getAllChecksByUser(user));

        return checks;
    }

    /*@GetMapping("/api/cashier/{id}/order-z")
    public Page<Check> getChecksAfterZ(@PathVariable(name = "id") User user,
                                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 6) Pageable pageable) {
        Page<Check> checks = checkService.getAllChecksByUser(user, pageable);

        checkService.clearChecks(checkService.getAllChecks());
        return checks;
    }*/
}
