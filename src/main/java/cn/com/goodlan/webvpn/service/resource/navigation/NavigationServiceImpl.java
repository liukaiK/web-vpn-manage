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
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
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
        List<Proxy> proxies = proxyRepository.findAllById(Arrays.asList(Convert.toLongArray(navigationDTO.getProxyIds())));
        Navigation navigation = new Navigation(navigationDTO.getName(), proxies);
        navigationRepository.save(navigation);
    }

    @Override
    public void update(NavigationDTO navigationDTO) {
        List<Proxy> proxies = proxyRepository.findAllById(Arrays.asList(Convert.toLongArray(navigationDTO.getProxyIds())));

        Navigation navigation = navigationRepository.getReferenceById(navigationDTO.getId());
        navigation.updateName(navigationDTO.getName());
        navigation.updateProxies(proxies);
        navigationRepository.save(navigation);
    }

    @Override
    public NavigationVO getById(Long id) {
        Navigation navigation = navigationRepository.getReferenceById(id);
        return NavigationMapper.INSTANCE.convert(navigation);
    }

    @Override
    public List<ProxyVO> selectProxyByNavigation(Long navigationId) {
        List<Proxy> proxies = proxyRepository.findAll();

        List<ProxyVO> proxyVOS = ProxyMapper.INSTANCE.convert(proxies);

        List<Proxy> roleList = navigationRepository.getReferenceById(navigationId).getProxies();

        for (ProxyVO proxyVO : proxyVOS) {
            for (Proxy role : roleList) {
                if (proxyVO.getId().equals(role.getId())) {
                    proxyVO.setCheck(true);
                }
            }
        }
        return proxyVOS;
    }

    @Override
    public void remove(String ids) {
        Long[] navigationIds = Convert.toLongArray(ids);
        navigationRepository.deleteAllById(Arrays.asList(navigationIds));
    }


}
