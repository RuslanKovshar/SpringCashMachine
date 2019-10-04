package ruslan.kovshar.final_project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ruslan.kovshar.final_project.repository.CheckRepository;
import ruslan.kovshar.final_project.repository.UserRepository;
import ruslan.kovshar.final_project.textcontants.Params;
import ruslan.kovshar.final_project.textcontants.URIs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ruslan.kovshar.final_project.controller.utils.CSRFTokenHolder.TOKEN_ATTR_NAME;
import static ruslan.kovshar.final_project.controller.utils.CSRFTokenHolder.getCsrfToken;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails("example@mail.com")
public class SeniorCashierControllerTest {

    @Autowired
    private SeniorCashierController seniorCashierController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckRepository checkRepository;

    private final CsrfToken CSRF_TOKEN = getCsrfToken();

    @Test
    public void contextLoads() {
        assertThat(seniorCashierController).isNotNull();
    }

    @Test
    public void xReportPage() throws Exception {
        this.mockMvc.perform(get(URIs.SENIOR_CASHIER + URIs.X_REPORT)
                .requestAttr("user", userRepository.findById(1L).get())
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void zReportPage() throws Exception {
        this.mockMvc.perform(get(URIs.SENIOR_CASHIER + URIs.Z_REPORT)
                .requestAttr("user", userRepository.findById(1L).get())
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void checksPage() throws Exception {
        this.mockMvc.perform(get(URIs.SENIOR_CASHIER + URIs.CHECKS)
                .requestAttr("user", userRepository.findById(1L).get())
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void cancelCheck() throws Exception {
        this.mockMvc.perform(post(URIs.SENIOR_CASHIER + URIs.CANCEL_CHECK)
                .sessionAttr(TOKEN_ATTR_NAME, CSRF_TOKEN)
                .param(CSRF_TOKEN.getParameterName(), CSRF_TOKEN.getToken())
                .requestAttr("user", userRepository.findById(1L).get())
                .param("id", "1")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(URIs.HOME));
    }

    @Test
    public void removeProduct() throws Exception {
        this.mockMvc.perform(post(URIs.SENIOR_CASHIER + URIs.CHECK + URIs.REMOVE_PRODUCT)
                .sessionAttr(TOKEN_ATTR_NAME, CSRF_TOKEN)
                .sessionAttr(Params.CHECK, checkRepository.findById(1L).get())
                .param(CSRF_TOKEN.getParameterName(), CSRF_TOKEN.getToken())
                .param("name", "Bread")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(URIs.CHECK));
    }
}