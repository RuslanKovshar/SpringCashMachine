package ruslan.kovshar.final_project.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.exceptions.ResourseNotFoundException;
import ruslan.kovshar.final_project.repository.ProductRepository;

import static ruslan.kovshar.final_project.textcontants.ExceptionsMessages.PRODUCT_NOT_FOUND;

@Service
public class ProductService {

    private static final Logger log = Logger.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean create(Product product) {
        try {
            productRepository.save(product);
            return true;
        }catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    public Product loadByCodeOrName(Integer code, String name) {
        return productRepository.findByCodeOrName(code, name)
                .orElseThrow(() -> new ResourseNotFoundException(PRODUCT_NOT_FOUND));
    }
}
