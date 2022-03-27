package com.sso.module.oauth.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 要暴露给第三方app的用户信息实体类
 *
 * @Author: golf
 * @Date: 2022/3/20 20:32
 */
@Data
public class SsoUser implements Serializable {
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 授权码
     */
    private String token;
    /**
     * 授权码有效时间单位小时
     */
    private Integer accessTokenValidTime;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * appId
     */
    private String appId;
    /**
     * app客户端指定跳转的地址
     */
    private String callBackUrl;
}
