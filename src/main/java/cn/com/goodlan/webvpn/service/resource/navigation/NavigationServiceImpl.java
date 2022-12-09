package cn.com.goodlan.webvpn.service.resource.navigation;

import cn.com.goodlan.webvpn.mapstruct.NavigationMapper;
import cn.com.goodlan.webvpn.mapstruct.ProxyMapper;
import cn.com.goodlan.webvpn.pojo.dto.NavigationDTO;
import cn.com.goodlan.webvpn.pojo.entity.resource.navigation.Navigation;
import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;
import cn.com.goodlan.webvpn.pojo.vo.NavigationVO;
import cn.com.goodlan.webvpn.pojo.vo.ProxyVO;
import cn.com.goodlan.webvpn.repository.resource.navigation.NavigationRepository;
import cn.com.goodlan.webvpn.repository.resource.proxy.ProxyRepository;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationRepository navigationRepository;

    @Autowired
    private ProxyRepository proxyRepository;


    @Override
    public Page<NavigationVO> search(Pageable pageable) {
        Page<Navigation> navigations = navigationRepository.findAll(pageable);
        List<NavigationVO> vos = NavigationMapper.INSTANCE.convert(navigations.getContent());
        return new PageImpl<>(vos, navigations.getPageable(), navigations.getTotalElements());
    }

    @Override
    public List<ProxyVO> selectProxyAll() {
        List<Proxy> proxies = proxyRepository.findAll();
        return ProxyMapper.INSTANCE.convert(proxies);
    }

    @Override
    public void save(NavigationDTO navigationDTO) {
        Long[] proxyIds = Convert.toLongArray(navigationDTO.getProxyIds());
        List<Proxy> proxies = proxyRepository.findAllById(Arrays.asList(proxyIds));
        Navigation navigation = new Navigation(navigationDTO.getName(), proxies);
        navigationRepository.save(navigation);
    }


}
