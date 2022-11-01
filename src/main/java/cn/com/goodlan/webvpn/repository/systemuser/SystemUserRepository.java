package cn.com.goodlan.webvpn.repository.systemuser;

import cn.com.goodlan.webvpn.pojo.entity.systemuser.SystemUser;
import cn.com.goodlan.webvpn.pojo.entity.systemuser.Username;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SystemUserRepository extends CustomizeRepository<SystemUser, Long> {

    Optional<SystemUser> getByUsername(Username username);

    @Modifying
    @Transactional
    @Query("update SystemUser set lastLoginTime = ?1 where id = ?2")
    void updateLastLoginTime(LocalDateTime localDateTime, Long userId);

    boolean existsByUsername(Username username);

    boolean existsByUsernameAndIdNot(Username username, Long userId);

}
