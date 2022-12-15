package cn.com.goodlan.webvpn.security.web.authentication;

import cn.com.goodlan.webvpn.exception.authentication.BadCaptchaException;
import cn.com.goodlan.webvpn.exception.authentication.CaptchaExpiredException;
import cn.com.goodlan.webvpn.exception.authentication.NotDeptException;
import cn.com.goodlan.webvpn.exception.authentication.UsernameMoreThanOneException;
import cn.com.goodlan.webvpn.pojo.Result;
import cn.com.goodlan.webvpn.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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
public class WebAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(WebAuthenticationSuccessHandler.class);

    private ObjectMapper objectMapper;

    public WebAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        Result result = null;
        if (exception instanceof UsernameNotFoundException) {
            result = Result.fail(302, "账号或密码错误");
        }
        if (exception instanceof BadCredentialsException) {
            result = Result.fail(302, "账号或密码错误");
        }
        if (exception instanceof BadCaptchaException) {
            result = Result.fail(302, "验证码错误！请重新输入");
        }
        if (exception instanceof CaptchaExpiredException) {
            result = Result.fail(302, "验证码已过期，情重新输入");
        }
        if (exception instanceof NotDeptException) {
            result = Result.fail(302, "没有配置部门,请联系超级管理员");
        }
        if (exception instanceof DisabledException) {
            result = Result.fail(302, "账户未激活,请联系超级管理员");
        }
        if (exception instanceof UsernameMoreThanOneException) {
            result = Result.fail(302, "用户数据出现错误,请联系超级管理员");
        }
        ResponseUtil.write(response, objectMapper.writeValueAsString(result), MediaType.APPLICATION_JSON_VALUE);
    }


}
