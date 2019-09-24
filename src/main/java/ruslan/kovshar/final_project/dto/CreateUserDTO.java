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

    @Pattern(regexp = "^([a-z0-9_.-]+)@([a-z0-9_-]+).([a-z]{2,6})$", message = "{input.email.message}")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.-_])(?=\\S+$).{8,}$" , message = "{input.password.message}")
    private String password;

    @NotBlank(message = "{input.firstNameUA.message}")
    private String firstNameUA;

    @NotBlank(message = "{input.secondNameUA.message}")
    private String secondNameUA;

    @NotBlank(message = "{input.firstNameEN.message}")
    private String firstNameEN;

    @NotBlank(message = "{input.secondNameEN.message}")
    private String secondNameEN;

}
