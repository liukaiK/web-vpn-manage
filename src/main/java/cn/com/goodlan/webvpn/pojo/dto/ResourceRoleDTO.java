package cn.com.goodlan.webvpn.pojo.dto;

import javax.validation.constraints.NotBlank;

public class ResourceRoleDTO {

    private Long id;

    @NotBlank(message = "请输入角色名称")
    private String roleName;

    private String navigationIds;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNavigationIds() {
        return navigationIds;
    }

    public void setNavigationIds(String navigationIds) {
        this.navigationIds = navigationIds;
    }

}
