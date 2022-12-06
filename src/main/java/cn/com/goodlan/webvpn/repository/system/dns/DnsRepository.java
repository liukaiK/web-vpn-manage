package cn.com.goodlan.webvpn.repository.system.dns;

import cn.com.goodlan.webvpn.pojo.entity.system.dns.Dns;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DnsRepository extends CustomizeRepository<Dns, Long> {

    Page<Dns> findByIpLike(String ip, Pageable pageable);

}
