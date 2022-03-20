package com.sso.common.exception;

import lombok.Getter;

/**
 * @Author: golf
 * @Date: 2022/3/20 23:31
 */
@Getter
public enum ResponseCodeEnum {
    SUCCESS(200, "success"),

    USER_NAME_OR_PASSWORD_ERROR(1000, "用户名或密码错误"),
    APP_ID_OR_SECRET_ERROR(1001, "appId或appSecret错误"),
    CODE_ID_INVALID(1002, "验证码已失效,请重新获取"),

    ;


    private final Integer code;
    private final String msg;

    /**
     * 根据错误码得到错误信息
     *
     * @param code 错误码
     * @return 错误信息
     */
    public static String getMsgByCode(Integer code) {
        for (ResponseCodeEnum errorCodeEnum : ResponseCodeEnum.values()) {
            if (code.equals(errorCodeEnum.getCode())) {
                return errorCodeEnum.getMsg();
            }
        }
        return "";
    }

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
