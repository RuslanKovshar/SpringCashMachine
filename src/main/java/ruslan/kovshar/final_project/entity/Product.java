package ruslan.kovshar.final_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ruslan.kovshar.final_project.enums.Types;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "products")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    protected Long id;

    @Column(nullable = false)
    protected Integer code;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected BigDecimal price;

    /*@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Types type;*/

   /* @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;*/

    public abstract BigDecimal calculatePrice(Number value);

    public Product(Integer code, String name, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
}
