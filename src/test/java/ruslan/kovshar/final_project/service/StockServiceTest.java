package ruslan.kovshar.final_project.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ruslan.kovshar.final_project.entity.CountProduct;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.Stock;
import ruslan.kovshar.final_project.exceptions.TransactionException;
import ruslan.kovshar.final_project.repository.StockRepository;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

    private Product product;
    private Stock stock;
    private final int OLD_VALUE = 1;

    @Before
    public void init() {
        product = new CountProduct();
        stock = new Stock();
        stock.setProduct(product);
        stock.setCountOfProduct(OLD_VALUE);
    }

    @Test(expected = TransactionException.class)
    public void updateFailed() throws TransactionException {
        int newValue = -2;
        Mockito.when(stockRepository.findByProduct(product)).thenReturn(Optional.of(stock));
        stockService.update(product, newValue);
    }

    @Test
    public void update() throws TransactionException {
        int newValue = 3;
        Mockito.when(stockRepository.findByProduct(product)).thenReturn(Optional.of(stock));
        stockService.update(product, newValue);
        Assert.assertEquals(stock.getCountOfProduct().intValue(), OLD_VALUE + newValue);
    }
}