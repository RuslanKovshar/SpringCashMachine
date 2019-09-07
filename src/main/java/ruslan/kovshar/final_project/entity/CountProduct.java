package ruslan.kovshar.final_project.entity;

import ruslan.kovshar.final_project.enums.Types;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CountProduct extends Product {

    @Override
    public BigDecimal calculatePrice(Number value) {
        return price.multiply(new BigDecimal(value.intValue()));
    }

    public CountProduct() {
    }

    public CountProduct(Integer code, String nameUA, String nameEN, BigDecimal price) {
        super(code, nameUA, nameEN, price);
    }
}
