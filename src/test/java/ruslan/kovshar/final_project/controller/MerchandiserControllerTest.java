package ruslan.kovshar.final_project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ruslan.kovshar.final_project.enums.Roles;
import ruslan.kovshar.final_project.enums.Types;
import ruslan.kovshar.final_project.repository.ProductRepository;
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
@WithUserDetails("example@mail.com")
public class MerchandiserControllerTest {

    @Autowired
    private MerchandiserController merchandiserController;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(merchandiserController).isNotNull();
    }

    @Test
    public void merchandiserPage() throws Exception {
        this.mockMvc.perform(get(URIs.MERCHANDISER))
                .andExpect(status().isOk());
    }

    @Test
    public void createProduct() throws Exception {
        CsrfToken csrfToken = getCsrfToken();
        this.mockMvc.perform(post(URIs.MERCHANDISER).sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .param("name", "Cacao")
                .param("code", "2222")
                .param("price", "15.99")
                .param("count", "5")
                .param("type", Types.PIECE_PRODUCT.name())
        ).andDo(print());

        assertThat(productRepository.findByCodeOrName(2222,"Cacao")).isNotNull();
    }

    @Test
    public void addCount() throws Exception {
        CsrfToken csrfToken = getCsrfToken();
        this.mockMvc.perform(post(URIs.MERCHANDISER + URIs.STOCK).sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .param("name","333")
                .param("countOfProduct","40")
        ).andDo(print());
    }
}