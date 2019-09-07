package ruslan.kovshar.final_project.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.entity.User;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ToString
//@NoArgsConstructor
//@AllArgsConstructor
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
