package ruslan.kovshar.final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Check {

    private Map<Product, BigDecimal> products = new HashMap<>();

    public void addProduct(Product product, Number value) {
        System.out.println(product.getClass().getName());
        products.putIfAbsent(product,product.calculatePrice(value));
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
