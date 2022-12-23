package cn.com.goodlan.webvpn.pojo.dto;

public class ProxyDTO {

    private Long id;

    private String name;

    private String virtualDomain;

    /**
     * 端口号
     */
    private String port;

    /**
     * 内网ip
     */
    private String ip;

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
