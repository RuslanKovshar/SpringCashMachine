package ruslan.kovshar.final_project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.repository.CheckRepository;

import java.util.List;
import java.util.Set;

@Service
public class CheckService {

    private final CheckRepository checkRepository;

    public CheckService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    public void saveCheck(Check check) {
        checkRepository.save(check);
    }

    public List<Check> getAllChecks() {
        return checkRepository.findAll();
    }

    public Page<Check> getAllChecksByUser(User user, Pageable pageable) {
        return checkRepository.findAllByUser(user, pageable);
    }

    public List<Check> getAllChecksByUser(User user) {
        return checkRepository.findAllByUser(user);
    }

    public void clearChecks(List<Check> checks) {
        checkRepository.deleteAll(checks);
    }

}
