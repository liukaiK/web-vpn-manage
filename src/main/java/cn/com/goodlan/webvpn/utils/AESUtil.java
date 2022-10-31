package cn.com.goodlan.webvpn.utils;

import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * AES加密解密工具
 *
 * @author liukai
 */
public class AESUtil {

    private final static AES AES = new AES(new byte[]{-51, 117, -22, 4, 118, 77, -26, -8, 36, -11, 51, -25, 62, 50, -65, 12});

    /**
     * 加密
     */
    public static String encrypt(String str) {
        return AES.encryptHex(str);
    }

    /**
     * 解密
     */
    public static String decrypt(String str) {
        return AES.decryptStr(str, StandardCharsets.UTF_8);
    }

}
