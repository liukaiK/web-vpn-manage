package cn.com.goodlan.webvpn.exception.authentication;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码过期
 *
 * @author liukai
 */
public class CaptchaExpiredException extends AuthenticationException {

    public CaptchaExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaExpiredException(String msg) {
        super(msg);
    }

}
