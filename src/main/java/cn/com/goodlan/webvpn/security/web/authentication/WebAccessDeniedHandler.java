package cn.com.goodlan.webvpn.security.web.authentication;

import cn.com.goodlan.webvpn.pojo.Result;
import cn.com.goodlan.webvpn.security.web.SecurityConstant;
import cn.com.goodlan.webvpn.utils.HttpUtil;
import cn.com.goodlan.webvpn.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WebAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(WebAccessDeniedHandler.class);

    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (HttpUtil.isAjaxRequest(request)) {
            ResponseUtil.write(response, objectMapper.writeValueAsString(Result.fail(500, "页面已经过期,请手动刷新页面")), MediaType.APPLICATION_JSON_VALUE);
        } else {
            response.sendRedirect(SecurityConstant.LOGIN_PAGE_URL);
        }
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
