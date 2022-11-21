package cn.com.goodlan.webvpn.repository.system.role;

import cn.com.goodlan.webvpn.pojo.entity.system.role.SystemRole;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemRoleRepository extends CustomizeRepository<SystemRole, Long> {

    @Query(value = "from SystemRole r left join r.admins u where u.id = ?1")
    List<SystemRole> findByUserList(Long userId);

    boolean existsByName(String roleName);

    boolean existsByNameAndIdNot(String roleName, Long roleId);

}
