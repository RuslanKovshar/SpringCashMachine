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
import ruslan.kovshar.final_project.entity.Buyer;
import ruslan.kovshar.final_project.entity.Check;
import ruslan.kovshar.final_project.entity.Product;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.repository.BuyerRepository;
import ruslan.kovshar.final_project.repository.CheckRepository;
import ruslan.kovshar.final_project.repository.ProductRepository;
import ruslan.kovshar.final_project.repository.UserRepository;
import ruslan.kovshar.final_project.view.Params;
import ruslan.kovshar.final_project.view.URIs;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ruslan.kovshar.final_project.controller.utils.CSRFTokenHolder.TOKEN_ATTR_NAME;
import static ruslan.kovshar.final_project.controller.utils.CSRFTokenHolder.getCsrfToken;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails("ruslan.kovshar@gmail.com")
public class ProductsControllerTest {

    @Autowired
    private ProductsController productsController;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private final CsrfToken CSRF_TOKEN = getCsrfToken();
    private final int TOTAL_PRICE = 30;

    @Test
    public void contextLoads() {
        assertThat(productsController).isNotNull();
    }

    @Test
    public void checkPage() throws Exception {
        Check check = checkRepository.findById(1L).get();
        this.mockMvc.perform(get(URIs.CHECK)
                .sessionAttr(Params.CHECK, check)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void productPage() throws Exception {
        Product product = productRepository.findById(1L).get();
        this.mockMvc.perform(get(URIs.CHECK + URIs.PRODUCT)
                .sessionAttr(Params.PRODUCT, product))
                .andDo(print());
    }

    @Test
    public void findProduct() throws Exception {
        this.mockMvc.perform(post(URIs.CHECK + URIs.PRODUCT)
                .sessionAttr(TOKEN_ATTR_NAME, CSRF_TOKEN)
                .param(CSRF_TOKEN.getParameterName(), CSRF_TOKEN.getToken())
                .param("name","222")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl( URIs.CHECK + URIs.PRODUCT));
    }

    @Test
    public void openCheck() throws Exception {
        this.mockMvc.perform(get(URIs.OPEN_CHECK))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(URIs.CHECK));
    }

    @Test
    public void closeCheck() throws Exception {
        Check check = checkRepository.findById(1L).get();
        this.mockMvc.perform(post(URIs.CLOSE_CHECK)
                .sessionAttr(TOKEN_ATTR_NAME, CSRF_TOKEN)
                .sessionAttr(Params.CHECK, check)
                .param(CSRF_TOKEN.getParameterName(), CSRF_TOKEN.getToken()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(URIs.PAYMENT +
                        Params.PARAM +
                        Params.VALUE + "=" +
                        check.getTotalPrice()));
    }

    @Test
    public void makePayment() throws Exception {
        Buyer buyer = buyerRepository.findById(1L).get();
        Check check = checkRepository.findById(1L).get();
        User user = userRepository.findById(1L).get();
        System.out.println(user.getCash());
        this.mockMvc.perform(post(URIs.PAYMENT)
                .sessionAttr(TOKEN_ATTR_NAME, CSRF_TOKEN)
                .sessionAttr(Params.CHECK, check)
                .requestAttr("user", user)
                .requestAttr("buyer", buyer)
                .param("value", Integer.toString(TOTAL_PRICE))
                .param(CSRF_TOKEN.getParameterName(), CSRF_TOKEN.getToken())
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(URIs.HOME));
    }

    @Test
    public void paymentPage() throws Exception {
        this.mockMvc.perform(get(URIs.PAYMENT + Params.PARAM + Params.VALUE + "=" + BigDecimal.valueOf(TOTAL_PRICE)))
                .andExpect(status().isOk());
    }

    @Test
    public void addProductToCheck() throws Exception {
        Check check = checkRepository.findById(1L).get();
        Product product = productRepository.findById(1L).get();
        this.mockMvc.perform(post(URIs.PRODUCT + URIs.ADD)
                .sessionAttr(TOKEN_ATTR_NAME, CSRF_TOKEN)
                .sessionAttr(Params.CHECK, check)
                .sessionAttr(Params.PRODUCT, product)
                .param(CSRF_TOKEN.getParameterName(), CSRF_TOKEN.getToken())
                .param("countOfProduct", "5")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(URIs.CHECK));
    }
}