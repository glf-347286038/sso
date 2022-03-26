package com.sso.common;

import lombok.Getter;

/**
 * @Author: golf
 * @Date: 2022/3/26 16:07
 */
@Getter
public enum RedisKeyDict {
    SSO_VERIFICATION_CODE("sso:verification:code", "sso验证码"),
    SSO_TOKEN("sso:token", "sso授权码"),
    ;
    private final String prefix;
    private final String description;

    RedisKeyDict(String prefix, String description) {
        this.prefix = prefix;
        this.description = description;
    }
}
