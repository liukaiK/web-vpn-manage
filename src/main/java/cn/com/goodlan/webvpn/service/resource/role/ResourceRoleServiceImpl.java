package cn.com.goodlan.webvpn.service.resource.role;

import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.mapstruct.NavigationMapper;
import cn.com.goodlan.webvpn.mapstruct.RoleMapper;
import cn.com.goodlan.webvpn.pojo.dto.ResourceRoleDTO;
import cn.com.goodlan.webvpn.pojo.entity.resource.navigation.Navigation;
import cn.com.goodlan.webvpn.pojo.entity.resource.role.ResourceRole;
import cn.com.goodlan.webvpn.pojo.vo.NavigationVO;
import cn.com.goodlan.webvpn.pojo.vo.ResourceRoleVO;
import cn.com.goodlan.webvpn.repository.resource.navigation.NavigationRepository;
import cn.com.goodlan.webvpn.repository.resource.role.ResourceRoleRepository;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceRoleServiceImpl implements ResourceRoleService {


    @Autowired
    private ResourceRoleRepository resourceRoleRepository;

    @Autowired
    private NavigationRepository navigationRepository;

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
        String navigationIds = resourceRoleDTO.getNavigationIds();

        if (checkRoleNameUnique(id, name)) {
            throw new BusinessException("角色名称已经存在!");
        }

        List<Navigation> navigations = navigationRepository.findAllById(Arrays.asList(Convert.toLongArray(navigationIds)));

        ResourceRole role = resourceRoleRepository.getReferenceById(id);
        role.updateName(name);
        role.updateDescription(description);
        role.updateNavigations(navigations);
        resourceRoleRepository.save(role);
    }

    @Override
    public void save(ResourceRoleDTO resourceRoleDTO) {
        String name = resourceRoleDTO.getRoleName();
        String description = resourceRoleDTO.getDescription();
        String navigationIds = resourceRoleDTO.getNavigationIds();
        if (StringUtils.isEmpty(name)) {
            throw new BusinessException("角色名称不能为空");
        }

        if (checkRoleNameUnique(name)) {
            throw new BusinessException("角色名称已经存在!");
        }

        List<Navigation> navigations = navigationRepository.findAllById(Arrays.asList(Convert.toLongArray(navigationIds)));

        ResourceRole role = new ResourceRole(name, description, navigations);
        resourceRoleRepository.save(role);
    }

    @Override
    public List<ResourceRoleVO> selectRoleAll() {
        List<ResourceRole> roleList = resourceRoleRepository.findAll();
        return RoleMapper.INSTANCE.convertResourceRole(roleList);
    }

    @Override
    public List<ResourceRoleVO> selectRoleByUser(Long userId) {
        List<ResourceRoleVO> allRole = selectRoleAll();
        List<ResourceRole> roleList = resourceRoleRepository.findByUserList(userId);
        for (ResourceRoleVO roleVO : allRole) {
            for (ResourceRole role : roleList) {
                if (roleVO.getId().equals(role.getId())) {
                    roleVO.setCheck(true);
                }
            }
        }
        return allRole;
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

    @Override
    public List<NavigationVO> selectNavigationAll() {
        List<Navigation> navigations = navigationRepository.findAll();
        return NavigationMapper.INSTANCE.convert(navigations);
    }

    @Override
    public List<NavigationVO> selectNavigationByRole(Long id) {
        List<Navigation> navigationsAll = navigationRepository.findAll();

        List<NavigationVO> navigationVOS = NavigationMapper.INSTANCE.convert(navigationsAll);

        List<Navigation> navigations = resourceRoleRepository.getReferenceById(id).getNavigations();

        for (NavigationVO navigationVO : navigationVOS) {
            for (Navigation navigation : navigations) {
                if (navigationVO.getId().equals(navigation.getId())) {
                    navigationVO.setCheck(true);
                }
            }
        }
        return navigationVOS;
    }

}
