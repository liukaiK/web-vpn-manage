package cn.com.goodlan.webvpn.exception.authentication;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * 用户名称重复了
 *
 * @author liukai
 */
public class UsernameMoreThanOneException extends InternalAuthenticationServiceException {


    public UsernameMoreThanOneException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameMoreThanOneException(String msg) {
        super(msg);
    }


}
