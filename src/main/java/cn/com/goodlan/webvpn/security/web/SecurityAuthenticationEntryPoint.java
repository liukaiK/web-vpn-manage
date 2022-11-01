package cn.com.goodlan.webvpn.security.web;

import cn.com.goodlan.webvpn.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liukai
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(SecurityAuthenticationEntryPoint.class);

    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (HttpUtil.isAjaxRequest(request)) {
//            ResponseUtil.write(response, objectMapper.writeValueAsString(Result.fail(403, "页面已经过期 请手动刷新页面")), MediaType.APPLICATION_JSON_VALUE);
        } else {
            response.sendRedirect("/login");
        }
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
