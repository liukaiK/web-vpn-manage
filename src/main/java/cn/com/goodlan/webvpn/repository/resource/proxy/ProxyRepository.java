package cn.com.goodlan.webvpn.repository.resource.proxy;

import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;

public interface ProxyRepository extends CustomizeRepository<Proxy, Long> {

    boolean existsByVirtualDomain(String virtualDomain);

}
