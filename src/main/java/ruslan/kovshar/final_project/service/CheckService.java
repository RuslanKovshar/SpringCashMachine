package ruslan.kovshar.final_project.service;

import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.entity.Check;
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

}
