package cn.com.goodlan.webvpn.service.role;

import cn.com.goodlan.webvpn.pojo.dto.RoleDTO;
import cn.com.goodlan.webvpn.pojo.vo.RoleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    Page<RoleVO> search(RoleDTO roleDTO, Pageable pageable);

    RoleVO getById(Long roleId);

    void update(RoleDTO roleDTO);

    /**
     * 保存角色
     */
    void save(RoleDTO roleDTO);

    /**
     * 角色下拉框
     */
    List<RoleVO> selectRoleAll();

    List<RoleVO> selectRoleByUser(Long userId);


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
}
