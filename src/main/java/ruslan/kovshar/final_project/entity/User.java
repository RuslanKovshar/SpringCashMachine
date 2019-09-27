package ruslan.kovshar.final_project.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ruslan.kovshar.final_project.enums.Roles;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import static ruslan.kovshar.final_project.view.TablesConstants.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_USERS_NAME, uniqueConstraints = {@UniqueConstraint(columnNames = {USER_EMAIL})})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USER_ID, nullable = false)
    private Long id;

    @Column(name = USER_EMAIL, nullable = false)
    private String email;

    @Column(name = USER_PASSWORD, nullable = false)
    private String password;

    @Column(name = FIRST_NAME_UA, nullable = false)
    private String firstNameUA;

    @Column(name = SECOND_NAME_UA, nullable = false)
    private String secondNameUA;

    @Column(name = FIRST_NAME_EN, nullable = false)
    private String firstNameEN;

    @Column(name = SECOND_NAME_EN, nullable = false)
    private String secondNameEN;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = USER_ROLES, joinColumns = @JoinColumn(name = CHECK_USER_ID))
    @Enumerated(EnumType.STRING)
    private Set<Roles> authorities;

    @Column(name = CASH)
    private BigDecimal cash;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = USER_MAPPING)
    private Set<Check> checks;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isCashier() {
        return authorities.contains(Roles.CASHIER);
    }

    public boolean isMerchandiser() {
        return authorities.contains(Roles.MERCHANDISER);
    }

    public boolean isSeniorCashier() {
        return authorities.contains(Roles.SENIOR_CASHIER);
    }
}
