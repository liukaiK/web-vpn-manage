package cn.com.goodlan.webvpn.controller.resource.navigation;

import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import cn.com.goodlan.webvpn.pojo.dto.NavigationDTO;
import cn.com.goodlan.webvpn.pojo.vo.NavigationVO;
import cn.com.goodlan.webvpn.service.resource.navigation.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 导航资源
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/resource/navigation")
public class NavigationController {

    @Autowired
    private NavigationService navigationService;

    @GetMapping
    @PreAuthorize("hasAuthority('resource:navigation:view')")
    public ModelAndView navigation() {
        return new ModelAndView("resource/navigation/navigation");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('resource:navigation:search')")
    public Page<NavigationVO> search(@PageableDefault Pageable pageable) {
        return navigationService.search(pageable);
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('resource:navigation:add')")
    public ModelAndView add(Model model) {
        model.addAttribute("proxies", navigationService.selectProxyAll());
        return new ModelAndView("resource/navigation/add");
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('resource:navigation:add')")
    public void add(NavigationDTO navigationDTO) {
        navigationService.save(navigationDTO);
    }


}
