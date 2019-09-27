package ruslan.kovshar.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import ruslan.kovshar.final_project.enums.Types;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class CreateProductDTO {

    @NotBlank(message = "{input.name.message}")
    private String name;

    @NotNull(message = "{input.code.message}")
    private Integer code;

    @NotNull(message = "{input.price.message}")
    private BigDecimal price;

    @NotNull(message = "{input.count.of.product.message}")
    private Integer count;

    @NotNull
    private Types type;

}
