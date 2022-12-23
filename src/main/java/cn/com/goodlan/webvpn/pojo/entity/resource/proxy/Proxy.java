package cn.com.goodlan.webvpn.pojo.entity.resource.proxy;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 代理实体
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "resource_proxy")
public class Proxy extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * 虚拟域名
     */
    private String virtualDomain;

    /**
     * 内网ip
     */
    private String ip;

    /**
     * 端口号
     */
    private String port;

    /**
     * 内网域名
     */
    private String url;

    /**
     * 协议
     */
    private String protocol;

    public void updateName(String name) {
        this.name = name;
    }

    public void updateVirDomainName(String virDomainName) {
        this.virtualDomain = virDomainName;
    }

    public void updatePort(String port) {
        this.port = port;
    }

    public void updateIp(String ip) {
        this.ip = ip;
    }

    public void updateUrl(String url) {
        this.url = url;
    }

    public void updateProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVirtualDomain() {
        return virtualDomain;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }

    public String getProtocol() {
        return protocol;
    }
}
