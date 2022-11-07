package cn.com.goodlan.webvpn.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * html调用 thymeleaf 实现参数管理
 *
 * @author liukai
 */
@Component("config")
public class ConfigComponent {

    @Autowired
    private ConfigService configService;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }

}
