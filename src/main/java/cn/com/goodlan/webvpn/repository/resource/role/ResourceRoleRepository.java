package cn.com.goodlan.webvpn.repository.resource.role;

import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResourceRoleRepository extends CustomizeRepository<ResourceRole, Long> {

    Page<ResourceRole> findByName(String name, Pageable pageable);

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, Long roleId);

}
