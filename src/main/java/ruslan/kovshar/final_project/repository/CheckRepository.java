package ruslan.kovshar.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruslan.kovshar.final_project.entity.Check;

public interface CheckRepository extends JpaRepository<Check, Long> {
}
