package cn.com.goodlan.webvpn.repository.resource.role;

import cn.com.goodlan.webvpn.pojo.entity.resource.role.Role;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleRepository extends CustomizeRepository<Role, Long> {

    Page<Role> findByName(String name, Pageable pageable);

}
