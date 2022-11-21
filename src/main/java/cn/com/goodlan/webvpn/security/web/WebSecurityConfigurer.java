package cn.com.goodlan.webvpn.security.web;

import cn.com.goodlan.webvpn.repository.system.admin.AdminRepository;
import cn.com.goodlan.webvpn.security.web.authentication.*;
import cn.com.goodlan.webvpn.security.xss.XssFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;

import java.util.Collections;

/**
 * SpringSecurity核心配置类
 *
 * @author liukai
 */
@Configuration
public class WebSecurityConfigurer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AdminRepository userRepository;

    @Autowired
    private RememberMeServices rememberMeServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain webLoginSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(SecurityConstant.LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl(SecurityConstant.LOGIN_PAGE_URL)
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().ignoringAntMatchers("/druid/**", "/common/upload")
                .and()
                .rememberMe().rememberMeServices(rememberMeServices)
                .and()
                .addFilterBefore(usernamePasswordCaptchaAuthenticationFilter(), RequestCacheAwareFilter.class)
                .addFilterBefore(new XssFilter(), WebAsyncManagerIntegrationFilter.class)
                .exceptionHandling().authenticationEntryPoint(securityAuthenticationEntryPoint());

        return http.build();
    }

    public UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter() {
        UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter = new UsernamePasswordCaptchaAuthenticationFilter();
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationManager(new ProviderManager(Collections.singletonList(authenticationProvider())));
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        usernamePasswordCaptchaAuthenticationFilter.setRememberMeServices(rememberMeServices);
        return usernamePasswordCaptchaAuthenticationFilter;
    }

    /**
     * 认证成功处理器
     */
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new WebAuthenticationSuccessHandler(objectMapper, userRepository);
    }

    /**
     * 认证失败处理器
     */
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new WebAuthenticationFailureHandler(objectMapper);
    }


    public AuthenticationProvider authenticationProvider() {
        UserDetailsAuthenticationProvider authenticationProvider = new UserDetailsAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }


    public AuthenticationEntryPoint securityAuthenticationEntryPoint() {
        SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint = new SecurityAuthenticationEntryPoint();
        securityAuthenticationEntryPoint.setObjectMapper(objectMapper);
        return securityAuthenticationEntryPoint;
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
