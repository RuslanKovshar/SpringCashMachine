package ruslan.kovshar.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import ruslan.kovshar.final_project.enums.Types;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateProductDTO {

    private String nameUA;
    private String nameEN;
    private Integer code;
    private BigDecimal price;
    private Types type;

}
