package cn.com.goodlan.webvpn.pojo.entity.config;

import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;

import javax.persistence.*;

/**
 * 系统设置
 *
 * @author liukai
 */
@Entity
@Table(name = "system_config")
public class Config extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String configName;

    private String configKey;

    private String configValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }


}
