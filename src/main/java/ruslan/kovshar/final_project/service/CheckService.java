package ruslan.kovshar.final_project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.repository.CheckRepository;

import java.util.List;

@Service
public class CheckService {

    private final CheckRepository checkRepository;
    private Check check;

    public CheckService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    public Check openCheck() {
        return new Check();
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

}
