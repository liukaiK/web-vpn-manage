package cn.com.goodlan.webvpn.security;

import cn.com.goodlan.webvpn.pojo.entity.menu.Menu;
import org.springframework.security.core.GrantedAuthority;

/**
 * 存放在SpringSecurity中的实体角色对象
 *
 * @author liukai
 */
public class SecurityAuthorityBean implements GrantedAuthority {

    private Long id;

    private String authority;

    private SecurityAuthorityBean(Menu menu) {
        this.id = menu.getId();
        this.authority = menu.getAuthority();
    }

    public static SecurityAuthorityBean convertFormMenu(Menu menu) {
        return new SecurityAuthorityBean(menu);
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
        SecurityAuthorityBean authority = (SecurityAuthorityBean) obj;
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
