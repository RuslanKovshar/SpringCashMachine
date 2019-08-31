package ruslan.kovshar.final_project.entity;

import lombok.Builder;
import ruslan.kovshar.final_project.enums.Types;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class WeightProduct extends Product {
    @Override
    public BigDecimal calculatePrice(Number value) {
        System.out.println("WEIGHT");
        return price.multiply(new BigDecimal(value.doubleValue()));
    }

    public WeightProduct() {
    }

    public WeightProduct(Integer code, String name, BigDecimal price) {
        super(code, name, price);
    }
}
