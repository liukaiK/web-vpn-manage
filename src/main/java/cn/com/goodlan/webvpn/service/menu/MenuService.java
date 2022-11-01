package cn.com.goodlan.webvpn.service.menu;

import cn.com.goodlan.webvpn.pojo.dto.MenuDTO;
import cn.com.goodlan.webvpn.pojo.vo.MenuVO;
import cn.com.goodlan.webvpn.pojo.vo.Ztree;

import java.util.List;

public interface MenuService {


    List<MenuVO> search(MenuDTO menuDTO);

    /**
     * 根据id查询一条记录
     */
    MenuVO getMenuById(Long id);

    List<Ztree> roleMenuTreeData(Long roleId);

    /**
     * 查询所有菜单信息
     *
     * @return 菜单列表
     */
    List<Ztree> menuTreeData();

    void save(MenuDTO menuDTO);

    void update(MenuDTO menuDTO);


    void deleteMenuById(Long menuId);

}
