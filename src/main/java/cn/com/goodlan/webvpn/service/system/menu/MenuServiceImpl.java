package cn.com.goodlan.webvpn.service.system.menu;

import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.mapstruct.MenuMapper;
import cn.com.goodlan.webvpn.pojo.dto.MenuDTO;
import cn.com.goodlan.webvpn.pojo.entity.menu.Menu;
import cn.com.goodlan.webvpn.pojo.entity.role.Role;
import cn.com.goodlan.webvpn.pojo.vo.MenuVO;
import cn.com.goodlan.webvpn.pojo.vo.Ztree;
import cn.com.goodlan.webvpn.repository.system.menu.MenuRepository;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 菜单Service
 *
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<MenuVO> search(MenuDTO menuDTO) {
        List<Menu> menuList;
        if (StringUtils.isNotEmpty(menuDTO.getName())) {
            menuList = menuRepository.findByName(menuDTO.getName() + "%");
        } else {
            menuList = menuRepository.findByOrderByParent();
        }
        return MenuMapper.INSTANCE.convertList(menuList);
    }

    @Override
    public MenuVO getMenuById(Long id) {
        Menu menu = menuRepository.getById(id);
        return MenuMapper.INSTANCE.convert(menu);
    }

    @Override
    public List<Ztree> roleMenuTreeData(Long roleId) {
        List<Menu> menuList = menuRepository.findAll();
        if (roleId != null) {
            List<String> menus = menuRepository.findAllByRoleId(roleId);
            return initZtree(menuList, menus, true);
        } else {
            return initZtree(menuList, null, true);
        }
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Ztree> menuTreeData() {
        List<Menu> menuList = menuRepository.findAll();
        return initZtree(menuList);
    }

    @Override
    public void save(MenuDTO menuDTO) {
        Menu menu = new Menu();
        menu.setName(menuDTO.getName());
        menu.setTarget(menuDTO.getTarget());
        menu.setAuthority(menuDTO.getAuthority());
        menu.setIcon(menuDTO.getIcon());
        menu.setUrl(menuDTO.getUrl());
        menu.setMenuType(menuDTO.getMenuType());
        menu.setUrl(menuDTO.getUrl());
        menu.setSort(menuDTO.getSort());
        if (menuDTO.getParentId() != 0L) {
            menu.addParent(menuDTO.getParentId());
        }
        menuRepository.save(menu);
    }

    @Override
    public void update(MenuDTO menuDTO) {
        Optional<Menu> menu = menuRepository.findById(menuDTO.getId());
        if (menu.isPresent()) {
            Menu menuFormDatabase = menu.get();
            menuFormDatabase.setName(menuDTO.getName());
            menuFormDatabase.setTarget(menuDTO.getTarget());
            menuFormDatabase.setAuthority(menuDTO.getAuthority());
            menuFormDatabase.setIcon(menuDTO.getIcon());
            menuFormDatabase.setUrl(menuDTO.getUrl());
            menuFormDatabase.setMenuType(menuDTO.getMenuType());
            menuFormDatabase.setUrl(menuDTO.getUrl());
            menuFormDatabase.setSort(menuDTO.getSort());
            if (ObjectUtil.isNotEmpty(menuDTO.getParentId())) {
                menuFormDatabase.addParent(menuDTO.getParentId());
            }
        }
    }

    @Override
    public void deleteMenuById(Long menuId) {
        Optional<Menu> menu = menuRepository.findById(menuId);
        if (menu.isPresent()) {
            if (menu.get().hasChildren()) {
                throw new BusinessException("存在子菜单,不允许删除");
            }
            if (menu.get().hasRole()) {
                throw new BusinessException("菜单已分配" + StringUtils.join(menu.get().getRoles().stream().map(Role::getName).toArray(), ",") + "角色,不允许删除 ");
            }
            menuRepository.deleteById(menuId);
        }

    }


    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList) {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList     菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag    是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList, List<String> roleMenuList, boolean permsFlag) {
        List<Ztree> ztrees = new ArrayList<>();
        boolean isCheck = CollectionUtil.isNotEmpty(roleMenuList);
        for (Menu menu : menuList) {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getId());
            if (menu.getParent() != null) {
                ztree.setParentId(menu.getParent().getId());
            }
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getName());
            if (isCheck) {
                ztree.setChecked(roleMenuList.contains(menu.getId() + menu.getAuthority()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(Menu menu, boolean permsFlag) {
        StringBuilder sb = new StringBuilder();
        sb.append(menu.getName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;").append(menu.getAuthority()).append("</font>");
        }
        return sb.toString();
    }

}
