package com.greatonce.oms.domain;

/**
 * OMS异常
 * code采用6位
 * 100000以内为通用异常，
 * 模块(两位，20开始）-异常码（4位）
 *
 * @author coby
 */
public class OmsException extends RuntimeException {

    private static final long serialVersionUID = 3620536764946863918L;
    private int code;

    public OmsException(String message) {
        super(message);
        this.code = 0;
    }

    public OmsException(int code, String message) {
        super(message);
        this.code = code;
    }

    public OmsException(int code, Throwable t) {
        super(t);
        this.code = code;
    }

    public OmsException(int code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
