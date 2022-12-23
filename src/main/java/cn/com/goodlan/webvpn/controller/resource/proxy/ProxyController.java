package cn.com.goodlan.webvpn.controller.resource.proxy;

import cn.com.goodlan.webvpn.annotations.Create;
import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import cn.com.goodlan.webvpn.pojo.dto.ProxyDTO;
import cn.com.goodlan.webvpn.pojo.vo.ProxyVO;
import cn.com.goodlan.webvpn.service.resource.proxy.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('resource:proxy:add')")
    public ModelAndView add(Model model) {
//        model.addAttribute("roles", roleService.selectRoleAll());
        return new ModelAndView("resource/proxy/add");
    }

    /**
     * 新增代理
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('resource:proxy:add')")
    public void add(@Validated(Create.class) ProxyDTO proxyDTO) {
        proxyService.save(proxyDTO);
    }


    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('resource:proxy:edit')")
    public ModelAndView edit(@PathVariable Long id, Model model) {
        model.addAttribute("proxy", proxyService.getById(id));
//        model.addAttribute("roles", roleService.selectRoleByUser(id));
        return new ModelAndView("resource/proxy/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('resource:proxy:edit')")
    public void edit(@Valid ProxyDTO userDTO) {
        proxyService.update(userDTO);
    }

    /**
     * 删除代理配置
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('resource:proxy:remove')")
    public void remove(String ids) {
//        userService.remove(ids);
    }


}
