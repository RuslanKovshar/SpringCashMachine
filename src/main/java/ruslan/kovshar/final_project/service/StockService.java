package ruslan.kovshar.final_project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.Stock;
import ruslan.kovshar.final_project.enums.Types;
import ruslan.kovshar.final_project.exceptions.ResourceNotFoundException;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.repository.StockRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ruslan.kovshar.final_project.textcontants.ExceptionsMessages.PRODUCT_NOT_FOUND;
import static ruslan.kovshar.final_project.textcontants.ExceptionsMessages.TRANSACTION_EXCEPTION;
import static ruslan.kovshar.final_project.textcontants.TextConstants.*;

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
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));

        if (stock.getCountOfProduct() + newCount < 0) {
            throw new TransactionException(TRANSACTION_EXCEPTION);
        }

        stock.setCountOfProduct(stock.getCountOfProduct() + newCount);
    }

    public List<Stock> getAllProductsFromStock() {
        return stockRepository.findAll();
    }

    public void printProductsToFile() {
        List<Stock> products = stockRepository.findAll();
        try (FileWriter fileWriter = new FileWriter(REPORT_FILE_PATH +
                "products" + TXT)) {
            fileWriter.write(String.format("%10s|", "Name"));
            fileWriter.write(String.format("%10s|", "Code"));
            fileWriter.write(String.format("%10s|", "Price"));
            fileWriter.write(String.format("%19s|", "Type"));
            fileWriter.write(String.format("%6s", "Count"));
            fileWriter.write(ENDL);
            fileWriter.write("-----------------------------------------------------------");
            fileWriter.write(ENDL);
            for (Stock stock : products) {
                Product product = stock.getProduct();
                Types type = product.getType();
                fileWriter.write(String.format("%10s|", product.getName()));
                fileWriter.write(String.format("%10s|", product.getCode()));
                fileWriter.write(String.format("%10s|", product.getPrice()));
                fileWriter.write(String.format("%19s|", type));
                if (type == Types.PIECE_PRODUCT) {
                    fileWriter.write(String.format("%5s", stock.getCountOfProduct()));
                } else {
                    fileWriter.write(String.format("%5s%1s", stock.getCountOfProduct(),"g"));
                }
                fileWriter.write(ENDL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
