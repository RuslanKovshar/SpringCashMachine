package ruslan.kovshar.final_project.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * User roles
 */
public enum Roles implements GrantedAuthority {
    CASHIER, SENIOR_CASHIER, MERCHANDISER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
