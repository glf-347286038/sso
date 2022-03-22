package com.sso.oauth.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: golf
 * @Date: 2022/3/20 20:32
 */
@Data
public class SsoUser implements Serializable {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * appId
     */
    private String appId;
    /**
     * app客户端指定跳转的地址
     */
    private String url;
}
