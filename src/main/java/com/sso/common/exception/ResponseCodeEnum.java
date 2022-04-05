package com.sso.common.exception;

import lombok.Getter;

/**
 * @Author: golf
 * @Date: 2022/3/20 23:31
 */
@Getter
public enum ResponseCodeEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "success"),
    PARAM_INVALID(1000, "参数非法"),

    USER_NAME_OR_PASSWORD_ERROR(2000, "用户名或密码错误"),
    APP_ID_OR_SECRET_ERROR(2001, "appId或appSecret错误"),
    CODE_ID_INVALID(2002, "验证码已失效,请重新获取"),
    USER_NOT_EXIST(2003, "用户不存在"),

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
