package cn.com.goodlan.webvpn.service.resource.role;

import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.mapstruct.RoleMapper;
import cn.com.goodlan.webvpn.pojo.dto.ResourceRoleDTO;
import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import cn.com.goodlan.webvpn.pojo.vo.ResourceRoleVO;
import cn.com.goodlan.webvpn.pojo.vo.RoleVO;
import cn.com.goodlan.webvpn.repository.resource.role.ResourceRoleRepository;
import cn.hutool.core.convert.Convert;
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
    private ResourceRoleRepository resourceRoleRepository;

    @Override
    public Page<ResourceRoleVO> search(String name, Pageable pageable) {
        Page<ResourceRole> page;
        if (StringUtils.isEmpty(name)) {
            page = resourceRoleRepository.findAll(pageable);
        } else {
            page = resourceRoleRepository.findByName(name, pageable);
        }
        List<ResourceRoleVO> list = RoleMapper.INSTANCE.convertResourceRole(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public ResourceRoleVO getById(Long id) {
        ResourceRole role = resourceRoleRepository.getReferenceById(id);
        return RoleMapper.INSTANCE.convert(role);
    }

    @Override
    public void update(ResourceRoleDTO resourceRoleDTO) {
        Long id = resourceRoleDTO.getId();
        String name = resourceRoleDTO.getRoleName();
        String description = resourceRoleDTO.getDescription();

        if (checkRoleNameUnique(id, name)) {
            throw new BusinessException("角色名称已经存在!");
        }
        ResourceRole role = resourceRoleRepository.getReferenceById(id);
        role.updateName(name);
        role.updateDescription(description);
        resourceRoleRepository.save(role);
    }

    @Override
    public void save(ResourceRoleDTO resourceRoleDTO) {
        String name = resourceRoleDTO.getRoleName();
        String description = resourceRoleDTO.getDescription();
        if (StringUtils.isEmpty(name)) {
            throw new BusinessException("角色名称不能为空");
        }

        if (checkRoleNameUnique(name)) {
            throw new BusinessException("角色名称已经存在!");
        }

        ResourceRole role = new ResourceRole(name, description);
        resourceRoleRepository.save(role);
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
        return resourceRoleRepository.existsByName(roleName);
    }

    @Override
    public boolean checkRoleNameUnique(Long roleId, String roleName) {
        return resourceRoleRepository.existsByNameAndIdNot(roleName, roleId);
    }


    @Override
    public void remove(String ids) {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            ResourceRole role = resourceRoleRepository.getReferenceById(roleId);
            if (role.hasUser()) {
                throw new BusinessException(String.format("%1$s已分配给用户,不能删除", role.getName()));
            }
            resourceRoleRepository.delete(role);
        }
    }

}
