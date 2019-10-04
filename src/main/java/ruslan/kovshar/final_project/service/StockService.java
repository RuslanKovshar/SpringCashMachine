package ruslan.kovshar.final_project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.Stock;
import ruslan.kovshar.final_project.exceptions.ResourseNotFoundException;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.repository.StockRepository;

import static ruslan.kovshar.final_project.textcontants.ExceptionsMessages.PRODUCT_NOT_FOUND;
import static ruslan.kovshar.final_project.textcontants.ExceptionsMessages.TRANSACTION_EXCEPTION;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void create(Stock stock) {
        stockRepository.save(stock);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = TransactionException.class)
    public void update(Product product, Integer newCount) throws TransactionException {
        Stock stock = stockRepository.findByProduct(product)
                .orElseThrow(() -> new ResourseNotFoundException(PRODUCT_NOT_FOUND));

        if (stock.getCountOfProduct() + newCount < 0) {
            throw new TransactionException(TRANSACTION_EXCEPTION);
        }

        stock.setCountOfProduct(stock.getCountOfProduct() + newCount);
    }
}
