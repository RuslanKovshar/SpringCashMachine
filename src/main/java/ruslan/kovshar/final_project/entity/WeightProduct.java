package ruslan.kovshar.final_project.entity;

import lombok.Builder;
import ruslan.kovshar.final_project.enums.Types;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Entity
public class WeightProduct extends Product {
    @Override
    public BigDecimal calculatePrice(Number value) {
        return price.divide(new BigDecimal(1000 / value.doubleValue()),2,RoundingMode.HALF_UP);
    }

    public WeightProduct() {
    }

    public WeightProduct(Integer code, String nameUA, String nameEN, BigDecimal price) {
        super(code, nameUA, nameEN, price);
    }
}
