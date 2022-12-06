package cn.com.goodlan.webvpn.controller.system.dns;

import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import cn.com.goodlan.webvpn.pojo.vo.DnsVO;
import cn.com.goodlan.webvpn.service.system.dns.DnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@ResponseResultBody
@RequestMapping("/system/dns")
public class DnsController {


    @Autowired
    private DnsService dnsService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:dns:view')")
    public ModelAndView dns() {
        return new ModelAndView("system/dns/dns");
    }


    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:dns:search')")
    public Page<DnsVO> search(String ip, @PageableDefault Pageable pageable) {
        return dnsService.search(ip, pageable);
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:dns:add')")
    public ModelAndView add(Model model) {
        return new ModelAndView("system/dns/add");
    }

    /**
     * 新增
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:dns:add')")
    public void add(String ip) {
        dnsService.save(ip);
    }


    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:dns:edit')")
    public ModelAndView edit(@PathVariable Long id, Model model) {
        model.addAttribute("dns", dnsService.getById(id));
        return new ModelAndView("system/dns/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:dns:edit')")
    public void edit(@Valid Long id, String ip) {
        dnsService.update(id, ip);
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('resource:dns:remove')")
    public void remove(String ids) {
        dnsService.remove(ids);
    }


}
