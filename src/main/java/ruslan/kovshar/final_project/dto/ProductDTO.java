package ruslan.kovshar.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import ruslan.kovshar.final_project.enums.Types;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductDTO {
    private String name;
    private Integer code;
    private BigDecimal price;
    private Types type;
}
