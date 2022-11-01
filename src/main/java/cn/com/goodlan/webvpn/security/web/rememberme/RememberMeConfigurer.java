package cn.com.goodlan.webvpn.security.web.rememberme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * 记住我
 *
 * @author liukai
 */
@Configuration
public class RememberMeConfigurer {


    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        return new TokenBasedRememberMeServices("remember-me", userDetailsService);
    }


}
