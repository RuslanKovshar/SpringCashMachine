package ruslan.kovshar.final_project.service;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.dto.CreateUserDTO;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.enums.Roles;
import ruslan.kovshar.final_project.exceptions.UserNotFoundException;
import ruslan.kovshar.final_project.repository.UserRepository;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        return User.builder()
                .email(createUserDTO.getEmail())
                .password(encoder.encode(createUserDTO.getPassword()))
                .firstNameUA(createUserDTO.getFirstNameUA())
                .secondNameUA(createUserDTO.getSecondNameUA())
                .firstNameEN(createUserDTO.getFirstNameEN())
                .secondNameEN(createUserDTO.getSecondNameEN())
                .authorities(Collections.singleton(Roles.CASHIER))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
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

    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return userRepository.findUserByEmail(s).orElseThrow(UserNotFoundException::new);
    }
}
