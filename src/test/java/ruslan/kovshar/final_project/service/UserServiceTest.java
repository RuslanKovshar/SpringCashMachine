package ruslan.kovshar.final_project.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void saveNewUser() {
        User user = new User();
        boolean isCreated = userService.saveNewUser(user);
        Assert.assertTrue(isCreated);
    }

    @Test
    public void saveNewUserFail() {
        User user = new User();
        Mockito.doThrow(RuntimeException.class).when(userRepository).save(user);
        boolean isCreated = userService.saveNewUser(user);
        Assert.assertFalse(isCreated);
    }
}