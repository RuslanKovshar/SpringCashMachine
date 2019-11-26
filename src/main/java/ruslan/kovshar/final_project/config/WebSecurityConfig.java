package ruslan.kovshar.final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ruslan.kovshar.final_project.enums.Roles;
import ruslan.kovshar.final_project.service.UserService;
import ruslan.kovshar.final_project.textcontants.Pages;
import ruslan.kovshar.final_project.textcontants.TextConstants;
import ruslan.kovshar.final_project.textcontants.URIs;

import java.util.Locale;

/**
 * configures web security
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public WebSecurityConfig(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(encoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/jquery/**").permitAll()
                .antMatchers(URIs.MERCHANDISER).hasAuthority(Roles.MERCHANDISER.name())
                .antMatchers(URIs.SENIOR_CASHIER + URIs.X_REPORT,
                        URIs.SENIOR_CASHIER + URIs.Z_REPORT,
                        URIs.SENIOR_CASHIER + URIs.CHECKS).hasAuthority(Roles.SENIOR_CASHIER.name())
                .antMatchers(URIs.ADMIN + URIs.USERS,
                        URIs.ADMIN + URIs.USER).hasAuthority(Roles.ADMIN.name())
                .antMatchers(URIs.LOGIN).permitAll()
                .antMatchers(URIs.REGISTRATION).permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe()
                .tokenValiditySeconds(86400)
                .and()
                .formLogin().defaultSuccessUrl(URIs.HOME, true).loginPage(URIs.LOGIN).permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage(URIs.FORBIDDEN);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName(TextConstants.LANG_PARAM_NAME);
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns(URIs.ANY_PATH);
    }
}
