package cn.com.goodlan.webvpn.service.resource.proxy;

import cn.com.goodlan.webvpn.mapstruct.ProxyMapper;
import cn.com.goodlan.webvpn.pojo.dto.ProxyDTO;
import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;
import cn.com.goodlan.webvpn.pojo.vo.ProxyVO;
import cn.com.goodlan.webvpn.repository.resource.proxy.ProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProxyServiceImpl implements ProxyService {


    @Autowired
    private ProxyRepository proxyRepository;

    @Override
    public void save(ProxyDTO proxyDTO) {
        Proxy proxy = new Proxy();
        proxy.updateName(proxyDTO.getName());
        proxy.updateVirDomainName(proxyDTO.getVirDomainName());
//        proxy.updateProxyIp(proxyDTO.getProxyIp());
//        proxy.updatePrefixType(proxyDTO.getPrefixType());
        proxyRepository.save(proxy);
    }

    @Override
    public ProxyVO getById(Long id) {
        Proxy proxy = proxyRepository.getReferenceById(id);
        return ProxyMapper.INSTANCE.convert(proxy);
    }

    @Override
    public void update(ProxyDTO proxyDTO) {
        Long id = proxyDTO.getId();

        Proxy proxy = proxyRepository.getReferenceById(id);
        proxy.updateName(proxyDTO.getName());
        proxy.updateVirDomainName(proxyDTO.getVirDomainName());
//        proxy.updateProxyIp(proxyDTO.getProxyIp());
//        proxy.updatePrefixType(proxyDTO.getPrefixType());
        proxyRepository.save(proxy);

    }

    @Override
    public void remove(Long id) {
        proxyRepository.deleteById(id);
    }

    @Override
    public Page<ProxyVO> search(ProxyDTO proxyDTO, Pageable pageable) {
        Page<Proxy> page = proxyRepository.findAll(pageable);
        List<ProxyVO> list = ProxyMapper.INSTANCE.convert(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

}
