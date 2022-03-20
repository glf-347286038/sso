package com.sso.common.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author golf
 */
@Getter
public final class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误提示
     */
    private final String message;

    public BizException(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMsg();
    }

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
