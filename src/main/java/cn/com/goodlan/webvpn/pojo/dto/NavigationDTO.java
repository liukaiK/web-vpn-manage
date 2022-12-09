package cn.com.goodlan.webvpn.pojo.dto;


public class NavigationDTO {

    private Long id;

    private String name;

    private String proxyIds;

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

    public String getProxyIds() {
        return proxyIds;
    }

    public void setProxyIds(String proxyIds) {
        this.proxyIds = proxyIds;
    }
}
