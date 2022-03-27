package com.sso.module.oauth.service;

import com.sso.module.oauth.model.SsoUser;
import com.sso.module.oauth.model.vo.OauthRequestVO;

/**
 * @Author: golf
 * @Date: 2022/3/20 20:29
 */
public interface OauthService {
    /**
     * 进行授权验证
     *
     * @param authorizeVO@return 回调地址
     */
    String authorize(OauthRequestVO.AuthorizeVO authorizeVO);

    /**
     * 根据验证码获得授权码
     *
     * @param accessTokenVO appId、appSecret、验证码
     * @return 授权码
     */
    String accessToken(OauthRequestVO.AccessTokenVO accessTokenVO);

    /**
     * 通过授权码获取用户信息
     *
     * @param token 授权码
     * @return 用户信息
     */
    SsoUser getSsoUserInfo(String token);

}
