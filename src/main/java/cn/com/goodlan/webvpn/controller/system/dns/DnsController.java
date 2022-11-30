package cn.com.goodlan.webvpn.controller.system.dns;

import cn.com.goodlan.webvpn.annotations.ResponseResultBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ResponseResultBody
@RequestMapping("/system/dns")
public class DnsController {


    @GetMapping
    @PreAuthorize("hasAuthority('system:dns:view')")
    public ModelAndView dns() {
        return new ModelAndView("system/dns/dns");
    }


}
