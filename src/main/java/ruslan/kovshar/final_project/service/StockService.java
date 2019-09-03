package ruslan.kovshar.final_project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.Stock;
import ruslan.kovshar.final_project.exceptions.ProductNotFoundException;
import ruslan.kovshar.final_project.exceptions.TransactionException;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = TransactionException.class)
    public void update(Product product, Integer newCount) throws TransactionException {
        Stock stock = stockRepository.findByProduct(product).orElseThrow(ProductNotFoundException::new);

        if (stock.getCountOfProduct() + newCount < 0) {
            throw new TransactionException("Not enough products in stock");
        }

        stock.setCountOfProduct(stock.getCountOfProduct() + newCount);
    }

}
