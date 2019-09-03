package ruslan.kovshar.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.Stock;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findByProduct(Product product);
}
