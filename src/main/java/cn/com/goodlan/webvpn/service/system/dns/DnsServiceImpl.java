package cn.com.goodlan.webvpn.service.system.dns;

import cn.com.goodlan.webvpn.mapstruct.DnsMapper;
import cn.com.goodlan.webvpn.pojo.entity.system.dns.Dns;
import cn.com.goodlan.webvpn.pojo.vo.DnsVO;
import cn.com.goodlan.webvpn.repository.system.dns.DnsRepository;
import cn.com.goodlan.webvpn.repository.system.dns.DomainRepository;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
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
public class DnsServiceImpl implements DnsService {

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private DnsRepository dnsRepository;


    @Override
    public Page<DnsVO> search(String ip, Pageable pageable) {
        Page<Dns> page;
        if (StrUtil.isNotEmpty(ip)) {
            page = dnsRepository.findByIpLike("%" + ip + "%", pageable);
        } else {
            page = dnsRepository.findAll(pageable);
        }
        List<DnsVO> list = DnsMapper.INSTANCE.convert(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(String ip) {
        Dns dns = new Dns(ip);
        dnsRepository.save(dns);
    }

    @Override
    public void remove(String ids) {
        Long[] dnsIds = Convert.toLongArray(ids);
        dnsRepository.deleteAllById(Arrays.asList(dnsIds));
    }

    @Override
    public DnsVO getById(Long id) {
        Dns dns = dnsRepository.getReferenceById(id);
        return DnsMapper.INSTANCE.convert(dns);
    }

    @Override
    public void update(Long id, String ip) {
        Dns dns = dnsRepository.getReferenceById(id);
        dns.updateIp(ip);
        dnsRepository.save(dns);
    }


}
