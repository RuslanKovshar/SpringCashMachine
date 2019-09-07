package ruslan.kovshar.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruslan.kovshar.final_project.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByCodeOrNameUAOrNameEN(Integer code, String nameUA,String nameEN);
}
