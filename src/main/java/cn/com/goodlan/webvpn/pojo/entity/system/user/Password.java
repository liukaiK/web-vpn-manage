package cn.com.goodlan.webvpn.pojo.entity.system.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

/**
 * @author liukai
 */
@Embeddable
public class Password {

    @Convert(converter = PasswordConverter.class)
    private String password;

    protected Password() {

    }

    public Password(String password) {
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    /**
     * 密码加密
     */
    public static class PasswordConverter implements AttributeConverter<String, String> {

        private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        @Override
        public String convertToDatabaseColumn(String attribute) {
            return passwordEncoder.encode(attribute);
        }

        @Override
        public String convertToEntityAttribute(String dbData) {
            return dbData;
        }

    }

}
