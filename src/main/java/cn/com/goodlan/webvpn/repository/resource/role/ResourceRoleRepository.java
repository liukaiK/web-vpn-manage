package cn.com.goodlan.webvpn.repository.resource.role;

import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRoleRepository extends CustomizeRepository<ResourceRole, Long> {


    @Query(value = "from ResourceRole r left join fetch r.users u where u.id = ?1")
    List<ResourceRole> findByUserList(Long userId);

    Page<ResourceRole> findByName(String name, Pageable pageable);

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, Long roleId);

}
