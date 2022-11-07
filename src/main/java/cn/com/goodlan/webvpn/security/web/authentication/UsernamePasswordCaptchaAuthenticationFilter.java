package cn.com.goodlan.webvpn.security.web.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liukai
 */
public class UsernamePasswordCaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public UsernamePasswordCaptchaAuthenticationFilter() {
        super();
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


//        if (postOnly && !request.getMethod().equals("POST")) {
//            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//        }

        String username = obtainUsername(request);

        String password = obtainPassword(request);

        String captcha = request.getParameter("captcha");

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        if (captcha == null) {
            captcha = "";
        }

        username = username.trim();

        UsernamePasswordCaptchaAuthenticationToken usernamePasswordCaptchaAuthenticationToken = new UsernamePasswordCaptchaAuthenticationToken(username, password, captcha);

        // Allow subclasses to set the "details" property
        setDetails(request, usernamePasswordCaptchaAuthenticationToken);

        return this.getAuthenticationManager().authenticate(usernamePasswordCaptchaAuthenticationToken);

    }


}
