package cn.com.goodlan.webvpn.security.web;

import cn.com.goodlan.webvpn.pojo.entity.menu.Menu;
import cn.com.goodlan.webvpn.pojo.entity.role.Role;
import cn.com.goodlan.webvpn.pojo.entity.systemuser.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 存放在SpringSecurity中的实体用户对象
 *
 * @author liukai
 */
public class SecurityUserBean implements UserDetails {

    private Long id;

    private String name;

    private String username;

    private String password;

    private SecurityCollegeBean college;

    private List<SecurityRoleBean> roleList;

    private List<SecurityAuthorityBean> authorities;

    private Integer sts;

    public static SecurityUserBean convertFromUser(SystemUser user) {
        return new SecurityUserBean(user);
    }

    private SecurityUserBean(SystemUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername().getUsername();
        this.password = user.getPassword().getPassword();
//        this.college = obtainCollege(user.getCollege());
//        this.roleList = obtainRoles(user.getRoleList());
//        this.authorities = obtainAuthorities(user.getRoleList());
    }

//    private SecurityCollegeBean obtainCollege(College college) {
//        return SecurityCollegeBean.convertFromCollege(college);
//    }

    private List<SecurityRoleBean> obtainRoles(List<Role> roleList) {
        List<SecurityRoleBean> roles = new ArrayList<>();
        for (Role role : roleList) {
            roles.add(new SecurityRoleBean(role));
        }
        return roles;
    }

    private List<SecurityAuthorityBean> obtainAuthorities(List<Role> roleList) {
        List<SecurityAuthorityBean> grantedAuthorities = new ArrayList<>();
        for (Role role : roleList) {
            List<Menu> menuList = role.getMenuList();
            for (Menu menu : menuList) {
                grantedAuthorities.add(SecurityAuthorityBean.convertFormMenu(menu));
            }
        }
        return grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public SystemUser castToUser() {
        return new SystemUser(this.id);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SecurityCollegeBean getCollege() {
        return college;
    }

    public void setCollege(SecurityCollegeBean college) {
        this.college = college;
    }

    public List<SecurityRoleBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SecurityRoleBean> roleList) {
        this.roleList = roleList;
    }

    public void setAuthorities(List<SecurityAuthorityBean> authorities) {
        this.authorities = authorities;
    }

    public Integer getSts() {
        return sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }
}
