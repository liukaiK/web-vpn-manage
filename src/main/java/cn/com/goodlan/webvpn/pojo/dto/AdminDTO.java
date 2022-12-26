package cn.com.goodlan.webvpn.pojo.dto;


import cn.com.goodlan.webvpn.annotations.Create;
import cn.com.goodlan.webvpn.annotations.Password;
import cn.com.goodlan.webvpn.annotations.Username;

public class AdminDTO {

    private Long id;

    private String name;

    /**
     * 只有新增用户的时候  才需要校验账号
     */
    @Username(groups = Create.class)
    private String username;

    /**
     * 只有新增用户的时候  才需要校验密码
     */
    @Password(groups = Create.class)
    private String password;

    private String roleIds;

    private String remark;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
