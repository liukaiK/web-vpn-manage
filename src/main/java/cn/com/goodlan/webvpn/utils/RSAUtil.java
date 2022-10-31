package cn.com.goodlan.webvpn.utils;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * 非对称RSA加密解密工具
 *
 * @author liukai
 */
public class RSAUtil {

    public static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKRhT3OkGbYrh9dKIEanY0F9Al35Cdedi4MqqCSykEchTE2kVA8G6heYnWzf3ktpmq5Ada5ZbyenoW0lpY3AaQGS8JgpBFqMKhnDDkoiTS5PTxg2D6nEP47Cf2rDvFqBPcLLPcGEthjP6UKsxnNVKlirGFCuogerzskon/i/JUvQIDAQAB";
    public static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIpGFPc6QZtiuH10ogRqdjQX0CXfkJ152LgyqoJLKQRyFMTaRUDwbqF5idbN/eS2markB1rllvJ6ehbSWljcBpAZLwmCkEWowqGcMOSiJNLk9PGDYPqcQ/jsJ/asO8WoE9wss9wYS2GM/pQqzGc1UqWKsYUK6iB6vOySif+L8lS9AgMBAAECgYACMKX4XHXZjjC52/UM+NqybIYhc28I73sdP8AvnB0kMuJdU8+w/Z3i9NfYDR8uVi4M5Nuw9t3zLTltzxsbOTDpZSGIRqOifZpnpNN67e29VHuEjwLHjp39RVOQB83eW/IGnFElpqMV2ejTaw9h4O6ZHfVzv/6hmNr3Md35uqBIQQJBAP4fIqm/h4OvkJuD0FodMseSJsbT2Sw0SoznBNR16agZlWrcyOzVPhAnH+GF9KBvsz3BiqWxtCsf+wpViScw2XkCQQCLS7tl6KI3mOyw/A9+ncIYPocHsIFHO5vMDsqJ3QCL96xIjxE//yS42hYaN+lg+h4QIGOYbM8Wv/PK/8iedshlAkEAiZ0OxmHj3toaMz1EfShGsXBh81YRyzBeFsOcB/n6mH+SPJZncGlh/JlTWGuUN7WFmTHQBT6gVKIy7ju31DNN8QJAOPdl0k2LWdVupwrOg2edIzEfI+PpWupj0tYRusPpH1TQ3nO0CEoeCIMYGuzWwtRHSy0TzhhhsNobp99nenb1iQJABRf4+jo/G5VQzXKHWwWsFyFiRX+144KHJgSZY2+DxwNfMCA6Fzjbb1+wTHFiuEJWlaRHueok0ERC+p9a0TmCsQ==";

    private final static RSA RSA = new RSA(PRIVATE_KEY, PUBLIC_KEY);


    /**
     * 加密
     */
    public static String encrypt(String str) {
        return RSA.encryptBcd(str, KeyType.PublicKey);
    }

    /**
     * 解密
     */
    public static String decrypt(String str) {
        return RSA.decryptStr(str, KeyType.PrivateKey);
    }

}
