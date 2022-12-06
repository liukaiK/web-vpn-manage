package cn.com.goodlan.webvpn.service.system.role;

import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.mapstruct.RoleMapper;
import cn.com.goodlan.webvpn.pojo.dto.RoleDTO;
import cn.com.goodlan.webvpn.pojo.entity.system.menu.Menu;
import cn.com.goodlan.webvpn.pojo.entity.system.role.SystemRole;
import cn.com.goodlan.webvpn.pojo.vo.RoleVO;
import cn.com.goodlan.webvpn.repository.system.role.SystemRoleRepository;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SystemRoleRepository roleRepository;

    @Override
    public Page<RoleVO> search(RoleDTO roleDTO, Pageable pageable) {
        Specification<SystemRole> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(roleDTO.getRoleName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + roleDTO.getRoleName() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<SystemRole> page = roleRepository.findAll(specification, pageable);
        List<RoleVO> list = RoleMapper.INSTANCE.convert(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public RoleVO getById(Long roleId) {
        SystemRole role = roleRepository.getReferenceById(roleId);
        return RoleMapper.INSTANCE.convert(role);
    }

    @Override
    public void update(RoleDTO roleDTO) {
        Long[] menuIds = Convert.toLongArray(roleDTO.getMenuIds());
        SystemRole role = roleRepository.getReferenceById(roleDTO.getId());
        role.updateName(roleDTO.getRoleName());
        role.updateRemark(roleDTO.getRemark());
        role.removeAllMenu();
        for (Long menuId : menuIds) {
            role.addMenu(new Menu(menuId));
        }
        roleRepository.save(role);
    }

    @Override
    public void save(RoleDTO roleDTO) {
        SystemRole role = new SystemRole();
        role.updateName(roleDTO.getRoleName());
        role.updateRemark(roleDTO.getRemark());
        Long[] menuIds = Convert.toLongArray(roleDTO.getMenuIds());
        for (Long menuId : menuIds) {
            role.addMenu(new Menu(menuId));
        }
        roleRepository.save(role);
    }

    @Override
    public List<RoleVO> selectRoleAll() {
        List<SystemRole> roleList = roleRepository.findAll();
        return RoleMapper.INSTANCE.convert(roleList);
    }

    @Override
    public List<RoleVO> selectRoleByUser(Long userId) {
        List<RoleVO> roleVOList = selectRoleAll();
        List<SystemRole> roleList = roleRepository.findByUserList(userId);
        for (RoleVO roleVO : roleVOList) {
            for (SystemRole role : roleList) {
                if (roleVO.getId().equals(role.getId())) {
                    roleVO.setCheck(true);
                }
            }
        }
        return roleVOList;
    }

    @Override
    public boolean checkRoleNameUnique(String roleName) {
        return roleRepository.existsByName(roleName);
    }

    @Override
    public boolean checkRoleNameUnique(Long roleId, String roleName) {
        return roleRepository.existsByNameAndIdNot(roleName, roleId);
    }


    @Override
    public void remove(String ids) {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            SystemRole role = roleRepository.getById(roleId);
            if (role.hasUser()) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getName()));
            }
            roleRepository.delete(role);
        }
    }

}
