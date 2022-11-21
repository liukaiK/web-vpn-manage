package cn.com.goodlan.webvpn.repository.school.user;

import cn.com.goodlan.webvpn.pojo.entity.school.user.SchoolUser;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Admin;
import cn.com.goodlan.webvpn.pojo.entity.system.user.Username;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SchoolUserRepository extends CustomizeRepository<SchoolUser, Long> {

    Optional<Admin> getByUsername(Username username);

    @Modifying
    @Transactional
    @Query("update Admin set lastLoginTime = ?1 where id = ?2")
    void updateLastLoginTime(LocalDateTime localDateTime, Long userId);

    boolean existsByUsername(Username username);

    boolean existsByUsernameAndIdNot(Username username, Long userId);

}
