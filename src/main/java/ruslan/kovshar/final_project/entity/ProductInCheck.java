package ruslan.kovshar.final_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.dto.CreateProductDTO;
import ruslan.kovshar.final_project.dto.GetProductDTO;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductInCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product product;

    private Number value;

    private BigDecimal price;

    /*@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_id")
    private Check check;*/
}
