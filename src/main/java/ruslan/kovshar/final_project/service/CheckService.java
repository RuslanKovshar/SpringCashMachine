package ruslan.kovshar.final_project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.repository.CheckRepository;

import java.util.List;

@Service
public class CheckService {

    private final CheckRepository checkRepository;

    public CheckService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    public void saveCheck(Check check) {
        checkRepository.save(check);
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

    public Check findCheckById(Long id) {
        //TODO изменить ексептион
        return checkRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteCheck(Check check) {
        checkRepository.delete(check);
    }
}
