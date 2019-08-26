package ruslan.kovshar.final_project.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    @Pattern(regexp = "^([a-z0-9_.-]+)@([a-z0-9_-]+).([a-z]{2,6})$",message = "{input.email}")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;
}
