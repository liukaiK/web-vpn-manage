package cn.com.goodlan.webvpn.service.resource.navigation;

import cn.com.goodlan.webvpn.pojo.dto.NavigationDTO;
import cn.com.goodlan.webvpn.pojo.vo.NavigationVO;
import cn.com.goodlan.webvpn.pojo.vo.ProxyVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NavigationService {

    Page<NavigationVO> search(Pageable pageable);

    List<ProxyVO> selectProxyAll();

    void save(NavigationDTO navigationDTO);

}
