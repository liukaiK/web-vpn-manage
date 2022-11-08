package cn.com.goodlan.webvpn.security.web.userdetails;

import cn.com.goodlan.webvpn.pojo.entity.system.menu.Menu;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * 存放在SpringSecurity中的实体角色对象
 *
 * @author liukai
 */
public class SecurityAuthority implements GrantedAuthority, Serializable {

    private Long id;

    private String authority;

    private SecurityAuthority(Menu menu) {
        this.id = menu.getId();
        this.authority = menu.getAuthority();
    }

    public static SecurityAuthority convertFormMenu(Menu menu) {
        return new SecurityAuthority(menu);
    }

    @Override
    public String getAuthority() {
        return authority;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        SecurityAuthority authority = (SecurityAuthority) obj;
        return this.id.equals(authority.getId());
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "SecurityAuthorityBean{" +
                "id='" + id + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
