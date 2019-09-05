package ruslan.kovshar.final_project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;

public interface CheckRepository extends JpaRepository<Check, Long> {
    void deleteAllByUser(User user);

    Page<Check> findAllByUser(User user, Pageable pageable);
}
