package ruslan.kovshar.final_project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.dto.UserDTO;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.enums.Roles;
import ruslan.kovshar.final_project.exceptions.UserNotFoundException;
import ruslan.kovshar.final_project.repository.UserRepository;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public boolean saveNewUser(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(encoder.encode(userDTO.getPassword()))
                .firstName(userDTO.getFirstName())
                .secondName(userDTO.getSecondName())
                .authorities(Collections.singleton(Roles.CASHIER))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return userRepository.findUserByEmail(s).orElseThrow(UserNotFoundException::new);
    }
}
