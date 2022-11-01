package cn.com.goodlan.webvpn.security.web;

import cn.com.goodlan.webvpn.exception.authentication.BadCaptchaException;
import cn.com.goodlan.webvpn.exception.authentication.CaptchaExpiredException;
import cn.com.goodlan.webvpn.exception.authentication.NotDeptException;
import cn.com.goodlan.webvpn.exception.authentication.UsernameMoreThanOneException;
import cn.com.goodlan.webvpn.pojo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 *
 * @author liukai
 */
public class LoginFailureHandler extends AbstractAuthenticationHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);

    private ObjectMapper objectMapper;

    public LoginFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        Integer message = null;
        Result result = null;
        if (exception instanceof UsernameNotFoundException) {
            message = 1;
            result = Result.fail(302, "账号或密码错误");
        }
        if (exception instanceof BadCredentialsException) {
            message = 2;
            result = Result.fail(302, "账号或密码错误");
        }
        if (exception instanceof BadCaptchaException) {
            message = 3;
            result = Result.fail(302, "验证码错误！请重新输入");
        }
        if (exception instanceof CaptchaExpiredException) {
            message = 4;
            result = Result.fail(302, "验证码已过期，情重新输入");
        }
        if (exception instanceof NotDeptException) {
            message = 5;
            result = Result.fail(302, "没有配置部门,请联系超级管理员");
        }
        if (exception instanceof DisabledException) {
            message = 6;
            result = Result.fail(302, "账户未激活,请联系超级管理员");
        }
        if (exception instanceof UsernameMoreThanOneException) {
            message = 7;
            result = Result.fail(302, "用户数据出现错误,请联系超级管理员");
        }
        try {
            loginLog(request, response, message);
        } catch (Exception e) {
            log.error("记录失败 记录登录日志发成异常", e);
        }
        handleResponse(request, response, objectMapper.writeValueAsString(result));

    }


    /**
     * 通过异常得到要返回给前台的内容
     */
//    public Result getContentByException(AuthenticationException exception) {
//        if (exception instanceof BadCredentialsException) {
//            return Result.fail("302", "账号或密码错误");
//        }
//        if (exception instanceof UsernameNotFoundException) {
//            return Result.fail("302", "账号或密码错误");
//        }
//        if (exception instanceof BadCaptchaException) {
//            return Result.fail("302", "验证码错误！请重新输入");
//        }
//        if (exception instanceof CaptchaExpiredException) {
//            return Result.fail("302", "验证码已过期，情重新输入");
//        }
//        throw new FrameworkException("未知的返回内容");
//    }

}
