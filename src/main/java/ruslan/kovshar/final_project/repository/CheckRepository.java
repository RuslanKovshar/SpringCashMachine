package ruslan.kovshar.final_project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.User;

import java.util.List;

public interface CheckRepository extends JpaRepository<Check, Long> {

    @Override
    void deleteAll(Iterable<? extends Check> iterable);

    Page<Check> findAllByUser(User user, Pageable pageable);

}
