package com.sso.module.oauth.service.impl;

import com.sso.common.exception.BizException;
import com.sso.common.exception.ResponseCodeEnum;
import com.sso.module.oauth.domain.SsoUser;
import com.sso.module.oauth.service.OauthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: golf
 * @Date: 2022/3/20 20:30
 */
@Log4j2
@Service
public class OauthServiceImpl implements OauthService {
    /**
     * 模拟数据库/缓存中的用户信息
     */
    private static final List<SsoUser> ssoUserList = new ArrayList<>();
    /**
     * 模数据库/缓存中appId和appSecret信息
     */
    private static final Map<String, String> appIdAppSecretMap = new HashMap<>();
    /**
     * 验证码和token的映射关系
     */
    private static final Map<String, String> codeTokenMap = new HashMap<>();
    /**
     * token和用户信息的映射关系
     */
    private static final Map<String, SsoUser> tokenSsoUserMap = new HashMap<>();

    private static final String FORMAT = "%s%s%s";

    static {
        String appId = "delta-test";
        SsoUser user = new SsoUser();
        user.setAppId(appId);
        user.setUserName("glf");
        user.setPassword("123456");
        user.setUrl("http://127.0.0.1:5000/callback");
        SsoUser user2 = new SsoUser();
        user2.setAppId(appId);
        user2.setUserName("dfb");
        user2.setPassword("123456");
        user2.setUrl("http://127.0.0.1:5000/callback");
        ssoUserList.add(user);
        ssoUserList.add(user2);
        appIdAppSecretMap.put(appId, "delta-test123456");
    }

    @Override
    public String authorize(String userName, String password, String appId) {
        // 校验用户是否存在
        SsoUser ssoUser = getSsoUser(appId, userName, password);
        if (ssoUser == null) {
            throw new BizException(ResponseCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        String token = UUID.randomUUID().toString();
        String code = token.substring(0, 6);
        addCodeAndTokenMap(appId, code, token, ssoUser);
        return String.format(FORMAT, ssoUser.getUrl(), "?code=", code);
    }

    @Override
    public String accessToken(String appId, String appSecret, String code) {
        if (!Objects.equals(appSecret, appIdAppSecretMap.get(appId))) {
            throw new BizException(ResponseCodeEnum.APP_ID_OR_SECRET_ERROR);
        }
        String token = codeTokenMap.get(String.format(FORMAT, appId, ":", code));
        if (token == null) {
            log.info("验证码:{}已失效", code);
            throw new BizException(ResponseCodeEnum.CODE_ID_INVALID);
        }
        return token;
    }

    @Override
    public SsoUser getSsoUserInfo(String token) {
        return tokenSsoUserMap.get(token);
    }

    @Override
    public SsoUser getSsoUser(String appId, String userName, String password) {
        for (SsoUser ssoUser : ssoUserList) {
            if (appId.equals(ssoUser.getAppId())
                    && userName.equals(ssoUser.getUserName())
                    && password.equals(ssoUser.getPassword())) {
                return ssoUser;
            }
        }
        return null;
    }

    @Override
    public void addCodeAndTokenMap(String appId, String code, String token, SsoUser ssoUser) {
        // 存验证码注意有效时间可以短一些
        codeTokenMap.put(String.format(FORMAT, appId, ":", code), token);
        tokenSsoUserMap.put(token, ssoUser);
    }
}
