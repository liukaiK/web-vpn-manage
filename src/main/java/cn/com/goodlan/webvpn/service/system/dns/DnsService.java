package cn.com.goodlan.webvpn.service.system.dns;

import cn.com.goodlan.webvpn.pojo.vo.DnsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DnsService {

    Page<DnsVO> search(String ip, Pageable pageable);


    void save(String ip);

    void remove(String ids);

    DnsVO getById(Long ip);

    void update(Long id, String ip);
}
