package cn.com.goodlan.webvpn.service.resource.role;

import cn.com.goodlan.webvpn.mapstruct.RoleMapper;
import cn.com.goodlan.webvpn.pojo.dto.RoleDTO;
import cn.com.goodlan.webvpn.pojo.entity.resource.role.Role;
import cn.com.goodlan.webvpn.pojo.vo.RoleVO;
import cn.com.goodlan.webvpn.repository.resource.role.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceRoleServiceImpl implements ResourceRoleService {


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<RoleVO> search(String name, Pageable pageable) {
        Page<Role> page;
        if (StringUtils.isEmpty(name)) {
            page = roleRepository.findAll(pageable);
        } else {
            page = roleRepository.findByName(name, pageable);
        }
        List<RoleVO> list = RoleMapper.INSTANCE.convertResourceRole(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public RoleVO getById(Long id) {
        Role role = roleRepository.getReferenceById(id);
        return RoleMapper.INSTANCE.convert(role);
    }

    @Override
    public void update(RoleDTO roleDTO) {
//        Long[] menuIds = Convert.toLongArray(roleDTO.getMenuIds());
//        SystemRole role = roleRepository.getById(roleDTO.getId());
//        role.updateName(roleDTO.getRoleName());
//        role.updateRemark(roleDTO.getRemark());
//        role.removeAllMenu();
//        for (Long menuId : menuIds) {
//            role.addMenu(new Menu(menuId));
//        }
//        roleRepository.save(role);
    }

    @Override
    public void save(RoleDTO roleDTO) {
//        SystemRole role = new SystemRole();
//        role.updateName(roleDTO.getRoleName());
//        role.updateRemark(roleDTO.getRemark());
//        Long[] menuIds = Convert.toLongArray(roleDTO.getMenuIds());
//        for (Long menuId : menuIds) {
//            role.addMenu(new Menu(menuId));
//        }
//        roleRepository.save(role);
    }

    @Override
    public List<RoleVO> selectRoleAll() {
//        List<SystemRole> roleList = roleRepository.findAll();
//        return RoleMapper.INSTANCE.convert(roleList);
        return null;
    }

    @Override
    public List<RoleVO> selectRoleByUser(Long userId) {
//        List<RoleVO> roleVOList = selectRoleAll();
//        List<SystemRole> roleList = roleRepository.findByUserList(userId);
//        for (RoleVO roleVO : roleVOList) {
//            for (SystemRole role : roleList) {
//                if (roleVO.getId().equals(role.getId())) {
//                    roleVO.setCheck(true);
//                }
//            }
//        }
//        return roleVOList;
        return null;
    }

    @Override
    public boolean checkRoleNameUnique(String roleName) {
//        return roleRepository.existsByName(roleName);
        return true;
    }

    @Override
    public boolean checkRoleNameUnique(Long roleId, String roleName) {
//        return roleRepository.existsByNameAndIdNot(roleName, roleId);
        return true;
    }


    @Override
    public void remove(String ids) {
//        Long[] roleIds = Convert.toLongArray(ids);
//        for (Long roleId : roleIds) {
//            SystemRole role = roleRepository.getById(roleId);
//            if (role.hasUser()) {
//                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getName()));
//            }
//            roleRepository.delete(role);
//        }
    }

}
