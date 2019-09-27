package ruslan.kovshar.final_project.entity;

import lombok.*;
import ruslan.kovshar.final_project.enums.Types;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import static ruslan.kovshar.final_project.view.TablesConstants.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = TABLE_PRODUCTS_NAME, uniqueConstraints =
        {@UniqueConstraint(columnNames = {CODE}),
                @UniqueConstraint(columnNames = {PRODUCT_NAME})})
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Long id;

    @Column(name = CODE, nullable = false)
    protected Integer code;

    @Column(name = PRODUCT_NAME, nullable = false)
    protected String name;

    @Column(nullable = false)
    protected BigDecimal price;

    @Enumerated(EnumType.STRING)
    protected Types type;

    public abstract BigDecimal calculatePrice(Number value);

    public Product(Integer code, String name, BigDecimal price, Types type) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                code.equals(product.code) &&
                name.equals(product.name) &&
                price.equals(product.price) &&
                type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, price, type);
    }
}
