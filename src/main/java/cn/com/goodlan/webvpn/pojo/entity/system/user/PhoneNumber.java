package cn.com.goodlan.webvpn.pojo.entity.system.user;


import cn.com.goodlan.webvpn.utils.AESUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Embeddable;

/**
 * @author liukai
 */
@Embeddable
public class PhoneNumber {

    @Convert(converter = PhoneNumberConverter.class)
    private String phoneNumber;

    protected PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 手机号码加密
     */
    @Converter
    public static class PhoneNumberConverter implements AttributeConverter<String, String> {

        @Override
        public String convertToDatabaseColumn(String attribute) {
            return AESUtil.encrypt(attribute);
        }

        @Override
        public String convertToEntityAttribute(String dbData) {
            return AESUtil.decrypt(dbData);
        }

    }

}
