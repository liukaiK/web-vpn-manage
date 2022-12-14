package cn.com.goodlan.webvpn.service.system.index;

import cn.com.goodlan.webvpn.pojo.entity.system.menu.Menu;

import java.util.List;

public interface IndexService {

    /**
     * 查询当前登录人所拥有的菜单
     *
     * @author liukai
     */
    List<Menu> findMenuByCurrentUser();

}
