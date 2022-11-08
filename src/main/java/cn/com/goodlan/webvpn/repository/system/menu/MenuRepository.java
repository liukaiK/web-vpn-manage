package cn.com.goodlan.webvpn.repository.system.menu;

import cn.com.goodlan.webvpn.pojo.entity.system.menu.Menu;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends CustomizeRepository<Menu, Long> {

    @Query("select distinct m from Menu m left join m.roles role left join role.users user where user.id = ?1 and m.menuType in ?2 order by m.sort")
    List<Menu> findByMenuTypeIn(Long userId, List<String> menuTypeList);


    @Query("from Menu m order by m.parent, m.sort")
    List<Menu> findByOrderByParent();

    @Query("from Menu m where m.name like ?1 order by m.parent, m.sort")
    List<Menu> findByName(String menuName);

    @Query("select concat(m.id,m.authority) from Menu m left join m.roles role where role.id = ?1")
    List<String> findAllByRoleId(Long roleId);

}
