package cn.com.goodlan.webvpn.pojo.entity.resource.proxy;

import cn.com.goodlan.webvpn.emums.BaseEnum;
import cn.com.goodlan.webvpn.exception.BusinessException;
import cn.com.goodlan.webvpn.pojo.entity.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理实体
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "resource_proxy")
public class Proxy extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * 虚拟域名
     */
    private String virDomainName;

    /**
     * 代理的内网ip
     */
    private String proxyIp;

    /**
     * 代理前缀的生成方式
     */
    @Convert(converter = PrefixTypeConverter.class)
    private PrefixType prefixType;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVirDomainName() {
        return virDomainName;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public PrefixType getPrefixType() {
        return prefixType;
    }

    public void updateName(String name) {
        this.name = name;
    }

    /**
     * 一定要实现BaseEnum
     *
     * @author liukai
     */
    public enum PrefixType implements BaseEnum {

        RANDOM(0, "乱序"), SPECIAL_CENSUS(1, "固定关键词");

        private static final Map<Integer, PrefixType> prefixTypes = new HashMap<>(16);

        PrefixType(Integer value, String description) {
            this.value = value;
            this.description = description;
        }

        private final Integer value;
        private final String description;

        @Override
        public Integer getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        static {
            for (PrefixType prefix : values()) {
                prefixTypes.put(prefix.getValue(), prefix);
            }
        }

        public static PrefixType get(Integer value) {
            PrefixType prefixType = prefixTypes.get(value);
            if (prefixType == null) {
                throw new BusinessException("Illegal value for prefixType: " + value);
            }
            return prefixType;
        }

    }

    public static class PrefixTypeConverter implements AttributeConverter<PrefixType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PrefixType attribute) {
            if (attribute == null) {
                throw new BusinessException("Unknown recType text  ");
            }
            return attribute.getValue();

        }

        @Override
        public PrefixType convertToEntityAttribute(Integer dbData) {
            for (PrefixType prefixType : PrefixType.values()) {
                if (prefixType.getValue().equals(dbData)) {
                    return prefixType;
                }
            }
            throw new BusinessException("Unknown prefixType text : " + dbData);
        }
    }


}
