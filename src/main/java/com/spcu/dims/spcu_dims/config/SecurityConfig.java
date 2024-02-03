package com.spcu.dims.spcu_dims.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final MyUserDetailsServices myUserDetailsServices;

    @Value("${app.remember.me.key}")
    private String myRememberMeKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
//                .csrf(
//                        csrf -> csrf.csrfTokenRepository(
//                                CookieCsrfTokenRepository.withHttpOnlyFalse())
//                )

                .csrf(
                        AbstractHttpConfigurer::disable
                )
                .rememberMe(
                        rememberMeKey -> rememberMeKey
                                .rememberMeServices(customRememberMeServices())
                                .key(myRememberMeKey)
                )
                .authorizeHttpRequests(
                        authorization -> authorization
                                .requestMatchers("/assets/**",
                                        "/assets/css/**",
                                        "/assets/images/**",
                                        "/assets/js/**", "/login", "/error", "/spcu-dims/api/v1/**")
                                .permitAll()
                                .requestMatchers("/dashboard", "/adminPage")
                                .authenticated()
                                .anyRequest()
                                .permitAll()

                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .maximumSessions(1) // Allow unlimited sessions
                                .maxSessionsPreventsLogin(true)
                                .expiredUrl("/login?error=expired")
                                .sessionRegistry(sessionRegistry())
                )
                .formLogin(
                        login -> login
                                .loginPage("/login")
                                .usernameParameter("username")
                                .failureHandler(customAuthenticationFailureHandler())
                                .defaultSuccessUrl("/dashboard?login_success=true")
                                .permitAll())
                .logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout_success=true")
                                .deleteCookies("JSESSIONID", "remember-me")
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .permitAll());
        return httpSecurity.build();
    }

    @Bean
    public RememberMeServices customRememberMeServices() {
        return new CustomRememberMeServices(myRememberMeKey, myUserDetailsServices);
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


}
