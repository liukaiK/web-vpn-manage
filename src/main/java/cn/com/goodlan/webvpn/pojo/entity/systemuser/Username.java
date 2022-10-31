package cn.com.goodlan.webvpn.pojo.entity.systemuser;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * 登录账号
 *
 * @author liukai
 */
@Embeddable
public class Username {

    private final static Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]{4,16}$");

    private String username;

    public Username() {

    }

    public Username(String username) {
//        if (!PATTERN.matcher(this.username).matches()) {
//            throw new DataValidException("账号格式不正确");
//        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
