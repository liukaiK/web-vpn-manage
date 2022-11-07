package cn.com.goodlan.webvpn.pojo.dto;

import javax.validation.constraints.NotBlank;

public class CollegeDTO {

    private String id;

    @NotBlank(message = "学院名称不能为空")
    private String name;

    private String parentId;

    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
