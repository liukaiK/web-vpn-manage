package cn.com.goodlan.webvpn.repository.role;

import cn.com.goodlan.webvpn.pojo.entity.role.Role;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends CustomizeRepository<Role, Long> {


    @Query(value = "from Role r left join r.userList u where u.id = ?1")
    List<Role> findByUserList(Long userId);

    boolean existsByName(String roleName);

    boolean existsByNameAndIdNot(String roleName, Long roleId);

}
