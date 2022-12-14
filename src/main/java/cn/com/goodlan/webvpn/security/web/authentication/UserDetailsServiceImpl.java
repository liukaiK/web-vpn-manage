package cn.com.goodlan.webvpn.security.web.authentication;

import cn.com.goodlan.webvpn.pojo.entity.system.user.Admin;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Username;
import cn.com.goodlan.webvpn.repository.system.admin.AdminRepository;
import cn.com.goodlan.webvpn.security.web.userdetails.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 登录获取用户信息
 *
 * @author liukai
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> userOptional = userRepository.getByUsername(new Username(username));

        Admin user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return SecurityUser.convertFromUser(user);
    }

}
