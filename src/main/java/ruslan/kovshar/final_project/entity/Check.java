package ruslan.kovshar.final_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static ruslan.kovshar.final_project.view.TablesConstants.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TABLE_CHECKS_NAME)
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<ProductInCheck> products = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CHECK_USER_ID)
    private User user;

    @Column(name = CHECK_TOTAL_PRICE)
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
