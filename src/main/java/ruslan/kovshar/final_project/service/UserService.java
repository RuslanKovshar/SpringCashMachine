package ruslan.kovshar.final_project.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.dto.CreateUserDTO;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.entity.UserRole;
import ruslan.kovshar.final_project.enums.Roles;
import ruslan.kovshar.final_project.exceptions.UserNotFoundException;
import ruslan.kovshar.final_project.repository.UserRepository;
import ruslan.kovshar.final_project.repository.UserRoleRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userRoleRepository = userRoleRepository;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        UserRole userRole = userRoleRepository.findByRole(Roles.CASHIER).get();
        return User.builder()
                .email(createUserDTO.getEmail())
                .password(encoder.encode(createUserDTO.getPassword()))
                .firstNameUA(createUserDTO.getFirstNameUA())
                .secondNameUA(createUserDTO.getSecondNameUA())
                .firstNameEN(createUserDTO.getFirstNameEN())
                .secondNameEN(createUserDTO.getSecondNameEN())
                .authorities(Collections.singleton(userRole))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .cash(BigDecimal.ZERO)
                .build();
    }

    public boolean saveNewUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return userRepository.findUserByEmail(s).orElseThrow(UserNotFoundException::new);
    }
}
