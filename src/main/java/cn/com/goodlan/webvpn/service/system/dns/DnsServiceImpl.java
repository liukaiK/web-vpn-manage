package cn.com.goodlan.webvpn.service.system.dns;

import cn.com.goodlan.webvpn.repository.system.dns.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnsServiceImpl implements DnsService {

    @Autowired
    private DomainRepository domainRepository;


}
