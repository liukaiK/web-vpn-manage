package cn.com.goodlan.webvpn.pojo.dto;

import javax.validation.constraints.NotBlank;

public class MenuDTO {

    private Long id;

    @NotBlank(message = "菜单名称不能为空")
    private String name;

    private String url;

    private Long parentId;

    /**
     * 菜单类型
     */
    @NotBlank(message = "请选择菜单类型")
    private String menuType;

    /**
     * 打开方式
     */
    private String target;


    private String authority;

    private String icon;

    private Integer sort;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
