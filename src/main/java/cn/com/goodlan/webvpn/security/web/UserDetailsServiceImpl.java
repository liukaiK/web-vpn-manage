package cn.com.goodlan.webvpn.security.web;

import cn.com.goodlan.webvpn.pojo.entity.systemuser.SystemUser;
import cn.com.goodlan.webvpn.pojo.entity.systemuser.Username;
import cn.com.goodlan.webvpn.repository.systemuser.SystemUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 登录获取用户信息
 *
 * @author liukai
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private SystemUserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SystemUser> userOptional = userRepository.getByUsername(new Username(username));

        SystemUser user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return SecurityUserBean.convertFromUser(user);
    }

    public void setUserRepository(SystemUserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
