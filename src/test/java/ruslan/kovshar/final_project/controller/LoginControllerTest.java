package ruslan.kovshar.final_project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ruslan.kovshar.final_project.view.Params;
import ruslan.kovshar.final_project.view.URIs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ruslan.kovshar.final_project.controller.utils.CSRFTokenHolder.TOKEN_ATTR_NAME;
import static ruslan.kovshar.final_project.controller.utils.CSRFTokenHolder.getCsrfToken;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(loginController).isNotNull();
    }

    @Test
    public void accessAllowed() throws Exception {
        CsrfToken csrfToken = getCsrfToken();
        this.mockMvc.perform(post(URIs.LOGIN).sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .param("username", "example@mail.com")
                .param("password", "1111"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void accessDenied() throws Exception {
        CsrfToken csrfToken = getCsrfToken();
        this.mockMvc.perform(post(URIs.LOGIN).sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .param("username", "wrong.email@mail.com")
                .param("password", "1111"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(URIs.LOGIN + Params.PARAM + Params.ERROR));
    }
}