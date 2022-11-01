package cn.com.goodlan.webvpn.utils;


import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liukai
 */
public abstract class UserAgentUtil {


    /**
     * 获取操作系统名称
     */
    public static String getOperatingSystem(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        return userAgent.getOperatingSystem().getName();
    }

    /**
     * 获取浏览器
     */
    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        return userAgent.getBrowser().getName();
    }

    private static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }


}
