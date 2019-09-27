package ruslan.kovshar.final_project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ruslan.kovshar.final_project.repository.UserRepository;
import ruslan.kovshar.final_project.view.URIs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ruslan.kovshar.final_project.controller.utils.CSRFTokenHolder.TOKEN_ATTR_NAME;
import static ruslan.kovshar.final_project.controller.utils.CSRFTokenHolder.getCsrfToken;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RegistrationControllerTest {


    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(registrationController).isNotNull();
    }

    @Test
    public void getRegistrationPage() throws Exception {
        this.mockMvc.perform(get(URIs.REGISTRATION))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void registrateNewUser() throws Exception {
        CsrfToken csrfToken = getCsrfToken();
        this.mockMvc.perform(post(URIs.REGISTRATION).sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .param("email", "bogdanvlas@gmail.com")
                .param("password", "$2a$08$Gz19FTxQLPuX2Sv8kVgyjuKPIX0XeyY.DoIJ53wsfP4Nl5jiT6YpO")
                .param("firstNameUA", "Богдан")
                .param("firstNameEN", "Bohdan")
                .param("secondNameUA", "Власюк")
                .param("secondNameEN", "Vlasyuk"))
        .andDo(print());

        assertThat(userRepository.findUserByEmail("bogdanvlas@gmail.com")).isNotNull();
    }
}