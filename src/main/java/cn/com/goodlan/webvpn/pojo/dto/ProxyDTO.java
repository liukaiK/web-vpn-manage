package cn.com.goodlan.webvpn.pojo.dto;

import cn.com.goodlan.webvpn.annotations.IP;

import javax.validation.constraints.NotBlank;

public class ProxyDTO {

    private Long id;

    @NotBlank(message = "代理名称不能为空")
    private String name;

    @NotBlank(message = "虚拟域名不能为空")
    private String virtualDomain;

    /**
     * 端口号
     */
    @NotBlank(message = "端口号不能为空")
    private String port;

    /**
     * 内网ip
     */
    @IP
    @NotBlank(message = "IP不能为空")
    private String ip;

    @NotBlank(message = "内网域名不能为空")
    private String url;

    /**
     * 协议
     */
    private String protocol;

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

    public String getVirtualDomain() {
        return virtualDomain;
    }

    public void setVirtualDomain(String virtualDomain) {
        this.virtualDomain = virtualDomain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

}
