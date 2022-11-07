package cn.com.goodlan.webvpn.controller.monitor;

import cn.com.goodlan.webvpn.pojo.domain.monitor.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器监控
 *
 * @author liukai
 */
@Controller
@RequestMapping("/monitor/server")
public class ServerController {

    private String prefix = "monitor/server";

    @GetMapping
    @PreAuthorize("hasAuthority('monitor:server:view')")
    public String server(ModelMap mmap) throws Exception {
        Server server = new Server();
        server.copyTo();
        mmap.put("server", server);
        return prefix + "/server";
    }

}
