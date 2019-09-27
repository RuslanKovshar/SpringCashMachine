package ruslan.kovshar.final_project.controller.utils;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

public class CSRFTokenHolder {

    public final static String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";

    public static CsrfToken getCsrfToken() {
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        return httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
    }
}
