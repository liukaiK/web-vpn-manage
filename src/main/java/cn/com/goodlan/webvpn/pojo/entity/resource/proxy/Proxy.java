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

    /**
     * 代理前缀的生成方式
     */
//    @Convert(converter = PrefixType.PrefixTypeConverter.class)
//    private PrefixType prefixType;
    public void updateName(String name) {
        this.name = name;
    }

    public void updateVirDomainName(String virDomainName) {
        this.virtualDomain = virDomainName;
    }

//    public void updatePrefixType(PrefixType prefixType) {
//        this.prefixType = prefixType;
//    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVirtualDomain() {
        return virtualDomain;
    }

//    public PrefixType getPrefixType() {
//        return prefixType;
//    }

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
