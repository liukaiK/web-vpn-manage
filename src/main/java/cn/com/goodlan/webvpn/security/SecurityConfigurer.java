package cn.com.goodlan.webvpn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SpringSecurity核心配置类
 *
 * @author liukai
 */
@Configuration
public class SecurityConfigurer {

    @Bean
    public SecurityFilterChain webLoginSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .headers().frameOptions().sameOrigin();
//                .addFilterBefore(usernamePasswordCaptchaAuthenticationFilter(), RequestCacheAwareFilter.class)
//                .addFilterBefore(new XssFilter(), WebAsyncManagerIntegrationFilter.class)
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        return http.build();
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
