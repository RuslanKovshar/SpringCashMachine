package ruslan.kovshar.final_project.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@EqualsAndHashCode
@Table(name = "products")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    protected Long id;

    @Column(nullable = false)
    protected Integer code;

    @Column(name = "name_UA", nullable = false)
    protected String nameUA;

    @Column(name = "name_EN", nullable = false)
    protected String nameEN;

    @Column(nullable = false)
    protected BigDecimal price;

    public abstract BigDecimal calculatePrice(Number value);

    public Product(Integer code, String nameUA, String nameEN, BigDecimal price) {
        this.code = code;
        this.nameUA = nameUA;
        this.nameEN = nameEN;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(code, product.code) &&
                Objects.equals(nameUA, product.nameUA) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, nameUA, price);
    }

    public String internationalName(String lang) {
        if (lang.equals("en")) {
            return nameEN;
        }
        return nameUA;
    }
}
