package ruslan.kovshar.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruslan.kovshar.final_project.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findByCodeOrName(Integer code, String name);
    Optional<Product> findByCode(Integer code);
}
