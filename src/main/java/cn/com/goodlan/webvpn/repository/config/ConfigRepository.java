package cn.com.goodlan.webvpn.repository.config;


import cn.com.goodlan.webvpn.pojo.entity.config.Config;
import cn.com.goodlan.webvpn.repository.CustomizeRepository;

public interface ConfigRepository extends CustomizeRepository<Config, Long> {

    Config getByConfigKey(String configKey);

}
