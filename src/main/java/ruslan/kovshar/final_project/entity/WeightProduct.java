package ruslan.kovshar.final_project.entity;

import ruslan.kovshar.final_project.enums.Types;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class WeightProduct extends Product {
    @Override
    public BigDecimal calculatePrice(Number value) {
        return price.divide(new BigDecimal(1000 / value.doubleValue()), 2, RoundingMode.HALF_UP);
    }

    public WeightProduct(Integer code, String name, BigDecimal price, Types type) {
        super(code, name, price, type);
    }
}
