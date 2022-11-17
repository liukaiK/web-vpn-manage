package cn.com.goodlan.webvpn.pojo.dto;

import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;

public class ProxyDTO {

    private Long id;

    private String name;

    private String virDomainName;

    private String proxyIp;

    private Proxy.PrefixType prefixType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVirDomainName() {
        return virDomainName;
    }

    public void setVirDomainName(String virDomainName) {
        this.virDomainName = virDomainName;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public Proxy.PrefixType getPrefixType() {
        return prefixType;
    }

    public void setPrefixType(Proxy.PrefixType prefixType) {
        this.prefixType = prefixType;
    }


}
