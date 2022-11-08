package cn.com.goodlan.webvpn.service.system.config;

import cn.com.goodlan.webvpn.repository.system.config.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public String selectConfigByKey(String configKey) {
        return configRepository.getByConfigKey(configKey).getConfigValue();
    }

}
