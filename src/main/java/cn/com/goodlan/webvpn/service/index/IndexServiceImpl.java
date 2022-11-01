package cn.com.goodlan.webvpn.service.index;

import cn.com.goodlan.webvpn.pojo.entity.menu.Menu;
import cn.com.goodlan.webvpn.repository.menu.MenuRepository;
import cn.com.goodlan.webvpn.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IndexServiceImpl implements IndexService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findMenuByCurrentUser() {
        return menuRepository.findByMenuTypeIn(SecurityUtil.getUserId(), Arrays.asList(Menu.M, Menu.C));
    }


}
