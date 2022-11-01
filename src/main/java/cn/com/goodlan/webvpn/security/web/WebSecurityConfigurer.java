package cn.com.goodlan.webvpn.security.web;

import cn.com.goodlan.webvpn.repository.systemuser.SystemUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;

import java.util.Arrays;

/**
 * SpringSecurity核心配置类
 *
 * @author liukai
 */
@Configuration
public class WebSecurityConfigurer {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain webLoginSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilterBefore(usernamePasswordCaptchaAuthenticationFilter(), RequestCacheAwareFilter.class)
//                .addFilterBefore(new XssFilter(), WebAsyncManagerIntegrationFilter.class)
                .exceptionHandling().authenticationEntryPoint(securityAuthenticationEntryPoint());

        return http.build();
    }

    public UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter() {
        UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter = new UsernamePasswordCaptchaAuthenticationFilter();
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationManager(new ProviderManager(Arrays.asList(authenticationProvider())));
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return usernamePasswordCaptchaAuthenticationFilter;
    }

    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        AuthenticationSuccessHandler authenticationSuccessHandler = new WebAuthenticationSuccessHandler(objectMapper, userRepository);
        return authenticationSuccessHandler;
    }

    public AuthenticationFailureHandler authenticationFailureHandler() {
        AuthenticationFailureHandler authenticationFailureHandler = new WebAuthenticationFailureHandler(objectMapper);
        return authenticationFailureHandler;
    }

    @Autowired
    private SystemUserRepository userRepository;

    public AuthenticationProvider authenticationProvider() {
        UserDetailsAuthenticationProvider authenticationProvider = new UserDetailsAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService(userRepository));
        return authenticationProvider;
    }

    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public AuthenticationEntryPoint securityAuthenticationEntryPoint() {
        SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint = new SecurityAuthenticationEntryPoint();
        securityAuthenticationEntryPoint.setObjectMapper(objectMapper);
        return securityAuthenticationEntryPoint;
    }

    @Bean
    public UserDetailsService userDetailsService(SystemUserRepository systemUserRepository) {
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setUserRepository(systemUserRepository);
        return userDetailsService;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(
                "/js/**",
                "/css/**",
                "/img/**",
                "/fonts/**",
                "/its/**",
                "/ajax/**",
                "/captcha.jpeg",
                "/plugins/**",
                "/favicon.ico"
        );
    }
}
