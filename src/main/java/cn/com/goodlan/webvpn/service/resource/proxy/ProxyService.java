package cn.com.goodlan.webvpn.service.resource.proxy;

import cn.com.goodlan.webvpn.pojo.dto.ProxyDTO;
import cn.com.goodlan.webvpn.pojo.vo.ProxyVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProxyService {

    void save(ProxyDTO proxyDTO);

    Page<ProxyVO> search(ProxyDTO proxyDTO, Pageable pageable);

}
