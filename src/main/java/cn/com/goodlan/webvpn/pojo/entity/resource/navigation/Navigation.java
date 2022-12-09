package cn.com.goodlan.webvpn.pojo.entity.resource.navigation;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 导航
 *
 * @author liukai
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "resource_navigation")
public class Navigation extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String proxyIds;

    private String proxyNames;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resource_proxy_navigation", joinColumns = @JoinColumn(name = "navigation_id"), inverseJoinColumns = @JoinColumn(name = "proxy_id"))
    private List<Proxy> proxies = new ArrayList<>();


    protected Navigation() {
    }

    public Navigation(String name, List<Proxy> proxies) {
        updateName(name);
        updateProxies(proxies);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateProxies(List<Proxy> proxies) {
        this.proxies = proxies;
        this.proxyIds = StringUtils.join(proxies.stream().map(Proxy::getId).collect(Collectors.toList()), "/");
        this.proxyNames = StringUtils.join(proxies.stream().map(Proxy::getName).collect(Collectors.toList()), "/");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProxyIds() {
        return proxyIds;
    }

    public String getProxyNames() {
        return proxyNames;
    }

    public List<Proxy> getProxies() {
        return proxies;
    }

}
