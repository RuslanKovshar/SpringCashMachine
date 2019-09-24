package ruslan.kovshar.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruslan.kovshar.final_project.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
