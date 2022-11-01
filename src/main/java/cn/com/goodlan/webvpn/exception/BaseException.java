package cn.com.goodlan.webvpn.exception;

/**
 * 系统顶级异常
 *
 * @author liukai
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
