package cn.com.goodlan.webvpn.security.captcha;

import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.generator.RandomGenerator;

/**
 * 验证码生成器
 *
 * @author liukai
 */
public class CaptchaGenerator {

    private static final String BASE_STR = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";

    private CaptchaGenerator() {

    }

    /**
     * 生成验证码
     */
    public static ICaptcha generate(int width, int height, int length, long expireTime) {
        Captcha captcha = new Captcha(width, height, length, expireTime);
        captcha.setGenerator(new RandomGenerator(BASE_STR, length));
        return captcha;
    }

}
