package cn.com.goodlan.webvpn.repository.resource.user;

import cn.com.goodlan.webvpn.pojo.entity.resource.user.User;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Username;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;

public interface UserRepository extends CustomizeRepository<User, Long> {

    boolean existsByUsername(Username username);

    boolean existsByUsernameAndIdNot(Username username, Long userId);

}
