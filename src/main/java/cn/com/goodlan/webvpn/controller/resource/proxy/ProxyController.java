package cn.com.goodlan.webvpn.controller.resource.proxy;

import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import cn.com.goodlan.webvpn.pojo.dto.ProxyDTO;
import cn.com.goodlan.webvpn.pojo.vo.ProxyVO;
import cn.com.goodlan.webvpn.service.resource.proxy.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 代理配置
 */
@RestController
@ResponseResultBody
@RequestMapping("/resource/proxy")
public class ProxyController {

    @Autowired
    private ProxyService proxyService;


    @GetMapping
    @PreAuthorize("hasAuthority('resource:proxy:view')")
    public ModelAndView proxy() {
        return new ModelAndView("resource/proxy/proxy");
    }


    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('resource:proxy:search')")
    public Page<ProxyVO> search(ProxyDTO proxyDTO, @PageableDefault Pageable pageable) {
        return proxyService.search(proxyDTO, pageable);
    }

}
