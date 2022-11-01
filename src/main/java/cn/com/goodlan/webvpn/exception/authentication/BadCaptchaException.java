package cn.com.goodlan.webvpn.exception.authentication;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误
 *
 * @author liukai
 */
public class BadCaptchaException extends AuthenticationException {


    public BadCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public BadCaptchaException(String msg) {
        super(msg);
    }

}
