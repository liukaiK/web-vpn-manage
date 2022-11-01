package cn.com.goodlan.webvpn.security.web;

import cn.com.goodlan.webvpn.utils.UserAgentUtil;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liukai
 */
public abstract class AbstractAuthenticationHandler {

//    @Autowired
//    private LoginLogService loginLogService;

    /**
     * 返回给前台JSON
     */
    protected void handleResponse(HttpServletRequest request, HttpServletResponse response, String responseBody) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.write(responseBody);
        writer.close();
    }


    /**
     * 记录登录日志
     *
     * @param message 是否登录成功
     */
    protected void loginLog(HttpServletRequest request, HttpServletResponse response, Integer message) {
        String os = UserAgentUtil.getOperatingSystem(request);
        String browser = UserAgentUtil.getBrowser(request);
//        String ip = IPUtil.getIpAddress(request);

//        LoginLog loginLog = new LoginLog();
//        loginLog.setUsername(request.getParameter("username"));
//        loginLog.setIp(ip);
//        loginLog.setOs(os);
//        loginLog.setBrowser(browser);
//        loginLog.setMessage(message);
//        loginLog.setLoginTime(LocalDateTime.now());
//        loginLogService.save(loginLog);

    }


}
