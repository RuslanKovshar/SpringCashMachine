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

    @NotBlank
    private String nameUA;

    @NotBlank
    private String nameEN;

    @NotNull
    private Integer code;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Types type;

    @NotNull
    private Integer count;

}
