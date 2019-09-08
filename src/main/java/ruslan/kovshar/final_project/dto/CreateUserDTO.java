package ruslan.kovshar.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@Setter
public class CreateUserDTO {

    @Pattern(regexp = "^([a-z0-9_.-]+)@([a-z0-9_-]+).([a-z]{2,6})$",message = "{input.email}")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstNameUA;

    @NotBlank
    private String secondNameUA;

    @NotBlank
    private String firstNameEN;

    @NotBlank
    private String secondNameEN;

}
