package cn.com.goodlan.webvpn.pojo.vo;

import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ProxyVO {

    private Integer id;

    private String name;


    /**
     * 虚拟域名
     */
    private String virDomainName;

    /**
     * 代理的内网ip
     */
    private String proxyIp;

    /**
     * 代理前缀的生成方式
     */
    private Proxy.PrefixType prefixType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
