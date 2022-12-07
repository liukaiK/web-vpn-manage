package cn.com.goodlan.webvpn.resource.proxy;

import cn.com.goodlan.webvpn.WebApplication;
import cn.com.goodlan.webvpn.pojo.entity.resource.proxy.Proxy;
import cn.com.goodlan.webvpn.repository.resource.proxy.ProxyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WithMockUser(username = "admin", password = "123433356")
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl")
public class ProxyServiceTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ProxyRepository proxyRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Transactional
    @Rollback(value = false)
    public void redis() throws JsonProcessingException {
        List<Proxy> proxies = proxyRepository.findAll();
        String key = "ips:url";
        redisTemplate.delete(key);
        for (Proxy proxy : proxies) {

            Map<String, String> childrenMap = new HashMap<>();
            childrenMap.put("url", proxy.getIp());
            childrenMap.put("port", proxy.getPort());
            childrenMap.put("web", proxy.getUrl());

            Map<String, Object> map = new HashMap<>();
            map.put(proxy.getVirtualDomain(), childrenMap);

            String content = objectMapper.writeValueAsString(map);
            System.out.println(content);

            redisTemplate.opsForSet().add(key, content);

        }


    }

}
