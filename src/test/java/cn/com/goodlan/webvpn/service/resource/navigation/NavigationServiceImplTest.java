package cn.com.goodlan.webvpn.service.resource.navigation;

import cn.com.goodlan.webvpn.WebApplication;
import cn.com.goodlan.webvpn.pojo.dto.NavigationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WithMockUser(username = "admin", password = "123433356")
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl")
public class NavigationServiceImplTest {

    @Autowired
    private NavigationService navigationService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void save() {
        NavigationDTO navigationDTO = new NavigationDTO();
        navigationDTO.setName("业务系统");
        navigationDTO.setProxyIds("");
        navigationService.save(navigationDTO);

    }
}