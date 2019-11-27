package ruslan.kovshar.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.entity.UserRole;
import ruslan.kovshar.final_project.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * DTO for display user
 */
@Setter
@Getter
public class GetUserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String secondName;
    private Set<GrantedAuthority> authorities;

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
        this.authorities = new HashSet<>(user.getAuthorities());
    }

    public boolean isCashier() {
        return authorities.contains(new UserRole(Roles.CASHIER));
    }

    public boolean isMerchandiser() {
        return authorities.contains(new UserRole(Roles.MERCHANDISER));
    }

    public boolean isSeniorCashier() {
        return authorities.contains(new UserRole(Roles.SENIOR_CASHIER));
    }

    public boolean isAdmin(){
        return authorities.contains(new UserRole(Roles.ADMIN));
    }
}
