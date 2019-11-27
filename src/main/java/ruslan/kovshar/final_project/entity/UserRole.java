package ruslan.kovshar.final_project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import ruslan.kovshar.final_project.enums.Roles;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_role")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return role.name();
    }

    public UserRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return role == userRole.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}

