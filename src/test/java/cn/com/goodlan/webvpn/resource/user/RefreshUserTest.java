package cn.com.goodlan.webvpn.resource.user;

import cn.com.goodlan.webvpn.ManageApplication;
import cn.com.goodlan.webvpn.pojo.entity.resource.user.User;
import cn.com.goodlan.webvpn.repository.resource.user.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WithMockUser(username = "admin", password = "123433356")
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl")
@SpringBootTest(classes = ManageApplication.class)
@RunWith(SpringRunner.class)
public class RefreshUserTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 刷新用户资源的角色相关的荣誉字段
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void refreshUserRoleIdsAndRoleNames() {
        List<User> users = entityManager.createQuery("from User as u left join fetch u.roles where u.id < 10000", User.class).getResultList();
//        for (User user : users) {
//            user.refreshRoles();
//        }
        userRepository.saveAll(users);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveToRedis() throws JsonProcessingException {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            map.put("username", user.getUsername());
            map.put("roleIds", user.getRoleIds());
            map.put("roleNames", user.getRoleNames());
            stringRedisTemplate.opsForHash().put("user", user.getUsername(), objectMapper.writeValueAsString(map));
        }


    }

}
