package cn.com.goodlan.webvpn.security.web.userdetails;

import cn.com.goodlan.webvpn.pojo.entity.system.menu.Menu;
import cn.com.goodlan.webvpn.pojo.entity.system.role.SystemRole;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 存放在SpringSecurity中的实体用户对象
 *
 * @author liukai
 */
public class SecurityUser implements UserDetails, Serializable {

    private Long id;

    private String name;

    private String username;

    private String password;

    private List<SecurityRole> securityRole;

    private List<SecurityAuthority> authorities;

    private Integer status;

    public static SecurityUser convertFromUser(Admin user) {
        return new SecurityUser(user);
    }

    private SecurityUser(Admin user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername().getUsername();
        this.password = user.getPassword().getPassword();
        this.securityRole = obtainRoles(user.getRoles());
        this.authorities = obtainAuthorities(user.getRoles());
    }

    private List<SecurityRole> obtainRoles(List<SystemRole> roleList) {
        List<SecurityRole> roles = new ArrayList<>();
        for (SystemRole role : roleList) {
            roles.add(new SecurityRole(role));
        }
        return roles;
    }

    private List<SecurityAuthority> obtainAuthorities(List<SystemRole> roleList) {
        List<SecurityAuthority> grantedAuthorities = new ArrayList<>();
        for (SystemRole role : roleList) {
            List<Menu> menuList = role.getMenus();
            for (Menu menu : menuList) {
                grantedAuthorities.add(SecurityAuthority.convertFormMenu(menu));
            }
        }
        return grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Admin castToUser() {
        return new Admin(this.id);
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

    public List<SecurityRole> getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(List<SecurityRole> securityRole) {
        this.securityRole = securityRole;
    }

    public void setAuthorities(List<SecurityAuthority> authorities) {
        this.authorities = authorities;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
