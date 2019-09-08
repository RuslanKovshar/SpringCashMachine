package ruslan.kovshar.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.enums.Types;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Locale;

@Getter
@Setter
public class GetProductDTO {
    private Long id;
    private Integer code;
    private String name;
    private BigDecimal price;
    private Types type;

    public GetProductDTO(Product product, SessionLocaleResolver resolver, HttpServletRequest req) {
        this.id = product.getId();
        this.code = product.getCode();
        this.price = product.getPrice();
        this.type = product.getType();
        Locale locale = resolver.resolveLocale(req);
        if (locale.equals(new Locale("ua"))) {
            this.name = product.getNameUA();
        } else {
            this.name = product.getNameEN();
        }
    }
}
