package cn.com.goodlan.webvpn.pojo.entity.resource.proxy;

import cn.com.goodlan.webvpn.emums.BaseEnum;
import cn.com.goodlan.webvpn.exception.BusinessException;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

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

    public static PrefixType getSourceForValue(Integer value) {
        PrefixType prefixType = prefixTypes.get(value);
        if (prefixType == null) {
            throw new BusinessException("Illegal value for prefixType: " + value);
        }
        return prefixType;
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
            return getSourceForValue(dbData);
        }

    }

}