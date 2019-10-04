package ruslan.kovshar.final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static ruslan.kovshar.final_project.textcontants.TablesConstants.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_STOCK_NAME)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = STOCK_ROW_ID)
    private Long id;

    @OneToOne
    private Product product;

    @Column(name = COUNT_OF_PRODUCTS)
    private Integer countOfProduct;
}
