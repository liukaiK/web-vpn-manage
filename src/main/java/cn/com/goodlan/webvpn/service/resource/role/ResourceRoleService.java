package cn.com.goodlan.webvpn.service.resource.role;

import cn.com.goodlan.webvpn.pojo.dto.ResourceRoleDTO;
import cn.com.goodlan.webvpn.pojo.vo.NavigationVO;
import cn.com.goodlan.webvpn.pojo.vo.ResourceRoleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResourceRoleService {

    Page<ResourceRoleVO> search(String name, Pageable pageable);

    ResourceRoleVO getById(Long roleId);

    void update(ResourceRoleDTO resourceRoleDTO);

    /**
     * 保存角色
     */
    void save(ResourceRoleDTO resourceRoleDTO);

    /**
     * 角色下拉框
     */
    List<ResourceRoleVO> selectRoleAll();

    List<ResourceRoleVO> selectRoleByUser(Long userId);


    /**
     * 检查角色名称是否已经存在
     *
     * @param roleName 角色名称
     * @return 是否存在
     */
    boolean checkRoleNameUnique(String roleName);

    /**
     * 检查账号是否已经存在, 排除userId
     *
     * @param roleId   角色ID
     * @param roleName 角色名称
     * @return 是否存在
     */
    boolean checkRoleNameUnique(Long roleId, String roleName);

    /**
     * 删除角色
     */
    void remove(String ids);

    List<NavigationVO> selectNavigationAll();

    List<NavigationVO> selectNavigationByRole(Long id);

}
