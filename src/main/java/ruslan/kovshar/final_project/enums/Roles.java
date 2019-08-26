package ruslan.kovshar.final_project.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    CASHIER, SENIOR_CASHIER, MERCHANDISER;

    @Override
    public String getAuthority() {
        return name();
    }
}
