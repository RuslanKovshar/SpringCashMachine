package ruslan.kovshar.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.enums.Types;

import java.math.BigDecimal;

@Getter
@Setter
public class GetProductDTO {
    private Long id;
    private Integer code;
    private String name;
    private BigDecimal price;
    private Types type;

    public GetProductDTO(Product product) {
        this.id = product.getId();
        this.code = product.getCode();
        this.price = product.getPrice();
        this.type = product.getType();
        this.name = product.getName();
    }
}
