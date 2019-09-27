package ruslan.kovshar.final_project.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ruslan.kovshar.final_project.entity.CountProduct;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.repository.ProductRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void create() {
        Product product = new CountProduct();

        boolean isCreated = productService.create(product);
        Assert.assertTrue(isCreated);
    }

    @Test
    public void createFailed() {
        Product product = new CountProduct();

        Mockito.doThrow(RuntimeException.class).when(productRepository).save(product);
        boolean isCreated = productService.create(product);
        Assert.assertFalse(isCreated);
    }
}