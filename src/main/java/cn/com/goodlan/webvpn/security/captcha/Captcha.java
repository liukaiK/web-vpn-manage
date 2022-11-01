package cn.com.goodlan.webvpn.security.captcha;

import cn.hutool.captcha.LineCaptcha;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图形验证码实体
 *
 * @author liukai
 */
public class Captcha extends LineCaptcha implements Serializable {

    /**
     * 验证码失效时间
     */
    private final LocalDateTime expireTime;

    public Captcha(int width, int height, int length, long expireTime) {
        super(width, height, length, 150);
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    /**
     * 验证码是否过期
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
