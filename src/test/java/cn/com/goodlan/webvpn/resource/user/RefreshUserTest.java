package cn.com.goodlan.webvpn.resource.user;

import cn.com.goodlan.webvpn.WebApplication;
import cn.com.goodlan.webvpn.pojo.entity.resource.user.User;
import cn.com.goodlan.webvpn.repository.resource.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@WithMockUser(username = "admin", password = "123433356")
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl")
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class RefreshUserTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

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

}
