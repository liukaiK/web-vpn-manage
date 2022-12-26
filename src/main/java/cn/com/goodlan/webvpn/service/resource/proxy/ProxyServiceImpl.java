package cn.com.goodlan.webvpn.service.resource.proxy;

import cn.com.goodlan.webvpn.exception.DataValidException;
import cn.com.goodlan.webvpn.mapstruct.ProxyMapper;
import cn.com.goodlan.webvpn.pojo.dto.ProxyDTO;
import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;
import cn.com.goodlan.webvpn.pojo.vo.ProxyVO;
import cn.com.goodlan.webvpn.repository.resource.proxy.ProxyRepository;
import cn.hutool.core.convert.Convert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
@Transactional
public class ProxyServiceImpl implements ProxyService {


    @Autowired
    private ProxyRepository proxyRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void save(ProxyDTO proxyDTO) {

        String virtualDomain = proxyDTO.getVirtualDomain();

        boolean exists = proxyRepository.existsByVirtualDomain(virtualDomain);

        if (exists) {
            throw new DataValidException("虚拟域名已经存在!");
        }


        Proxy proxy = new Proxy();
        proxy.updateName(proxyDTO.getName());
        proxy.updateVirDomainName(virtualDomain);
        proxy.updatePort(proxyDTO.getPort());
        proxy.updateIp(proxyDTO.getIp());
        proxy.updateUrl(proxyDTO.getUrl());
        proxy.updateProtocol(proxyDTO.getProtocol());
        proxyRepository.save(proxy);

        refreshCache();
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
//        proxy.updateName(proxyDTO.getName());
        proxy.updateVirDomainName(proxyDTO.getVirtualDomain());
//        proxy.updateProxyIp(proxyDTO.getProxyIp());
//        proxy.updatePrefixType(proxyDTO.getPrefixType());
        proxyRepository.save(proxy);


        refreshCache();


    }

    @Override
    public void remove(String ids) {
        proxyRepository.deleteAllById(Arrays.asList(Convert.toLongArray(ids)));
        refreshCache();
    }

    private void refreshCache() {
        redisTemplate.delete("ips:url");
        redisTemplate.delete("cachehtml");
        List<Proxy> proxies = proxyRepository.findAll();
        String[] str = new String[proxies.size()];
        for (int i = 0; i < proxies.size(); i++) {
            Map<String, String> childrenMap = new HashMap<>();
            childrenMap.put("url", proxies.get(i).getIp());
            childrenMap.put("port", proxies.get(i).getPort());
            childrenMap.put("web", proxies.get(i).getUrl());

            Map<String, Object> map = new HashMap<>();
            map.put(proxies.get(i).getVirtualDomain(), childrenMap);
            String content;
            try {
                content = objectMapper.writeValueAsString(map);
                str[i] = content;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
        redisTemplate.opsForSet().add("ips:url", str);

    }

    @Override
    public Page<ProxyVO> search(ProxyDTO proxyDTO, Pageable pageable) {
        Specification<Proxy> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(proxyDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + proxyDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(proxyDTO.getVirtualDomain())) {
                list.add(criteriaBuilder.like(root.get("virtualDomain").as(String.class), "%" + proxyDTO.getVirtualDomain() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<Proxy> page = proxyRepository.findAll(specification, pageable);
        List<ProxyVO> list = ProxyMapper.INSTANCE.convert(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

}
