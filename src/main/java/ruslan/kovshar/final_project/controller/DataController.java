package ruslan.kovshar.final_project.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.service.CheckService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ruslan.kovshar.final_project.view.RequestParams.ID_PARAM;
import static ruslan.kovshar.final_project.view.URIs.*;

@RestController
public class DataController {

    private final CheckService checkService;

    public DataController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping(API + CASHIER + ID + X_REPORT)
    public Page<Check> getChecksAfterX(@PathVariable(name = ID_PARAM) User user,
                                       @PageableDefault(sort = {ID_PARAM}, direction = Sort.Direction.DESC, size = 6) Pageable pageable) {
        return checkService.getAllChecksByUser(user, pageable);
    }

    @GetMapping(API + CASHIER + ID + Z_REPORT)
    public List<Check> getChecksAfterZ(@PathVariable(name = ID_PARAM) User user) {
        user.setChecks(Collections.emptySet());
        List<Check> checks = new ArrayList<>(checkService.getAllChecksByUser(user));
        checkService.clearChecks(checkService.getAllChecksByUser(user));
        return checks;
    }
}
