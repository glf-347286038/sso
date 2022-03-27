package com.sso.module.oauth.service;

import com.sso.module.oauth.model.SsoUser;

/**
 * @Author: golf
 * @Date: 2022/3/20 20:29
 */
public interface OauthService {
    /**
     * 进行授权验证
     *
     * @param userName 用户名
     * @param password 密码
     * @param appId    appId
     * @return 回调地址
     */
    String authorize(String userName, String password, String appId);

    /**
     * 根据验证码获得授权码
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @param code      验证码
     * @return 授权码
     */
    String accessToken(String appId, String appSecret, String code);

    /**
     * 通过授权码获取用户信息
     *
     * @param token 授权码
     * @return 用户信息
     */
    SsoUser getSsoUserInfo(String token);

    /**
     * 获取用户信息
     * 之后需要改为从数据库中/缓存中查询
     *
     * @param appId    appId
     * @param userName 用户名
     * @param password 用户密码
     * @return 用户信息
     */
    SsoUser getSsoUser(String appId, String userName, String password);

    /**
     * 添加验证码、token、用户的映射关系
     * 之后需要改为添加到缓存中,要考虑过期时间问题
     *
     * @param appId   appId
     * @param code    验证码
     * @param token   授权码
     * @param ssoUser 用户信息
     */
    void addCodeAndTokenMap(String appId, String code, String token, SsoUser ssoUser);
}
