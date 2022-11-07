package cn.com.goodlan.webvpn.controller.monitor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控
 *
 * @author liukai
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController {

    private String prefix = "/druid";

    @GetMapping
    @PreAuthorize("hasAuthority('monitor:data:view')")
    public String index() {
        return "redirect:" + prefix + "/index.html";
    }

}
