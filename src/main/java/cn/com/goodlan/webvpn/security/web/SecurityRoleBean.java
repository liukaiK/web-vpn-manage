package cn.com.goodlan.webvpn.security.web;

import cn.com.goodlan.webvpn.pojo.entity.role.Role;

public class SecurityRoleBean {

    private Long id;

    private String name;

    public SecurityRoleBean(Role role) {
        this.id = role.getId();
        this.name = role.getName();
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

}
