package cn.com.goodlan.webvpn.security.web.authentication;

import cn.com.goodlan.webvpn.pojo.Result;
import cn.com.goodlan.webvpn.repository.system.admin.AdminRepository;
import cn.com.goodlan.webvpn.security.web.AbstractAuthenticationHandler;
import cn.com.goodlan.webvpn.utils.SecurityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 登录成功处理器
 *
 * @author liukai
 */
public class WebAuthenticationSuccessHandler extends AbstractAuthenticationHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(WebAuthenticationSuccessHandler.class);

    private final ObjectMapper objectMapper;

    private final AdminRepository userRepository;

    public WebAuthenticationSuccessHandler(ObjectMapper objectMapper, AdminRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        try {
            loginLog(request, response, 0);
        } catch (Exception e) {
            log.error("登录成功 记录登录日志发成异常", e);
        }


        updateLastLoginTime();

        handleResponse(request, response, objectMapper.writeValueAsString(Result.success()));

    }

    private void updateLastLoginTime() {
        userRepository.updateLastLoginTime(LocalDateTime.now(), SecurityUtil.getAdminId());
    }


}
