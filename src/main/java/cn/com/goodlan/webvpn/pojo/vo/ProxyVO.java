package cn.com.goodlan.webvpn.pojo.vo;

import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.PrefixType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ProxyVO {

    private Long id;

    private String name;


    /**
     * 虚拟域名
     */
    private String virtualDomain;

    /**
     * 代理的内网ip
     */
    private String ip;

    private String port;

    private String url;

    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * 代理前缀的生成方式
     */
    private PrefixType prefixType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

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

    public String getVirtualDomain() {
        return virtualDomain;
    }

    public void setVirtualDomain(String virtualDomain) {
        this.virtualDomain = virtualDomain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public PrefixType getPrefixType() {
        return prefixType;
    }

    public void setPrefixType(PrefixType prefixType) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
