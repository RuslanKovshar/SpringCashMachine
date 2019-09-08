package ruslan.kovshar.final_project.service;

import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.exceptions.ProductNotFoundException;
import ruslan.kovshar.final_project.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(Product product) {
        productRepository.save(product);
    }

    public Product loadByCodeOrName(Integer code, String name) {
        return productRepository.findByCodeOrNameUAOrNameEN(code,name,name).orElseThrow(ProductNotFoundException::new);
    }
}
