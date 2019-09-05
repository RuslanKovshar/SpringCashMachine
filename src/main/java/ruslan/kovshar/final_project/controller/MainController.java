package ruslan.kovshar.final_project.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.service.CheckService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
public class MainController {

    private final CheckService checkService;

    public MainController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/dai")
    public List<Check> get() {
        return checkService.getAllChecks();
    }

    @GetMapping("/senior_cashier_menu/cashier/{id}/checks")
    public Page<Check> getAll(@PathVariable(name = "id") User user,
                             @PageableDefault(sort = {"id"},direction = Sort.Direction.ASC,size = 5) Pageable pageable){
        //Page<Check> page = user.getChecks();
        //Page<Check> page = checkService.getAllChecksByUser(user,pageable);
        return checkService.getAllChecksByUser(user,pageable);
    }
}
