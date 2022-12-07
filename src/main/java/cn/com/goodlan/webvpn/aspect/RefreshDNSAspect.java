package cn.com.goodlan.webvpn.aspect;

import cn.com.goodlan.webvpn.pojo.entity.system.dns.Dns;
import cn.com.goodlan.webvpn.repository.system.dns.DnsRepository;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class RefreshDNSAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DnsRepository dnsRepository;

    private final String redisKey = "dns:url";

    @Pointcut("@annotation( cn.com.goodlan.webvpn.annotations.RefreshDNS)")
    public void refreshDNSPointCut() {
    }

    @AfterReturning("refreshDNSPointCut()")
    public void refreshRedis() {
        redisTemplate.delete(redisKey);
        List<Dns> dnsList = dnsRepository.findAll();

        String[] value = dnsList.stream().map(Dns::getIp).toArray(String[]::new);


        redisTemplate.opsForSet().add(redisKey, value);

    }


}
