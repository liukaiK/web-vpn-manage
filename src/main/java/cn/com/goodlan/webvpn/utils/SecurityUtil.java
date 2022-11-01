package cn.com.goodlan.webvpn.utils;

import cn.com.goodlan.webvpn.security.web.SecurityUserBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取当前登录人工具类
 *
 * @author liukai
 */
public abstract class SecurityUtil {


    public static SecurityUserBean getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SecurityUserBean) authentication.getPrincipal();
    }

    /**
     * 获取当前登录人的用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }


    /**
     * 获取当前登录人的登录账号
     */
    public static String getUsername() {
        return getUser().getUsername();
    }

    /**
     * 获取当前登录人角色ID
     *
     * @return 角色ID集合
     */
//    public static List<String> getRoleId() {
//        return getUser().getRoleList().stream().map(SecurityRoleBean::getId).collect(Collectors.toList());
//    }

    /**
     * 获取当前登录人角色名称
     *
     * @return 角色名称集合
     */
//    public static List<String> getRoleName() {
//        return getUser().getRoleList().stream().map(SecurityRoleBean::getRoleName).collect(Collectors.toList());
//    }


//    public static String getUserId() {
//        User user = getUser();
//        return
//    }

}
