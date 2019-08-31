package ruslan.kovshar.final_project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruslan.kovshar.final_project.entity.Stock;
import ruslan.kovshar.final_project.repository.StockRepository;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void create(Stock stock) {
        stockRepository.save(stock);
    }

    @Transactional
    public void update(Long productId, Integer newCount) {
        Stock stock = stockRepository.findByProductId(productId).orElseThrow(RuntimeException::new);
        stock.setCountOfProduct(stock.getCountOfProduct() + newCount);
    }
}
