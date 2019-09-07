package ruslan.kovshar.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Setter
@Getter
public class GetUserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String secondName;

    public GetUserDTO(User user, SessionLocaleResolver resolver, HttpServletRequest req) {
        this.id = user.getId();
        this.email = user.getEmail();
        Locale locale = resolver.resolveLocale(req);
        if (locale.equals(new Locale("ua"))) {
            this.firstName = user.getFirstNameUA();
            this.secondName = user.getSecondNameUA();
        } else {
            this.firstName = user.getFirstNameEN();
            this.secondName = user.getSecondNameEN();
        }
    }
}
