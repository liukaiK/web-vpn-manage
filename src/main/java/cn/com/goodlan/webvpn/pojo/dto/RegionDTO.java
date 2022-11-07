package cn.com.goodlan.webvpn.pojo.dto;

import javax.validation.constraints.NotBlank;

public class RegionDTO {

    private String id;

    @NotBlank(message = "区域名称不能为空")
    private String name;

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

}
