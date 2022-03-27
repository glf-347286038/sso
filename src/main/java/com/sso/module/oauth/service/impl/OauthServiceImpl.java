package com.sso.module.oauth.service.impl;

import com.sso.common.RedisKeyDict;
import com.sso.common.exception.BizException;
import com.sso.common.exception.ResponseCodeEnum;
import com.sso.module.app.service.AppDetailService;
import com.sso.module.oauth.model.SsoUser;
import com.sso.module.oauth.model.vo.OauthRequestVO;
import com.sso.module.oauth.service.OauthService;
import com.sso.module.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: golf
 * @Date: 2022/3/20 20:30
 */
@Log4j2
@Service
public class OauthServiceImpl implements OauthService {
    private static final String FORMAT = "%s%s%s";

    private final UserService userService;
    private final AppDetailService appDetailService;
    private final RedisTemplate<String, Object> redisTemplate;

    public OauthServiceImpl(UserService userService, AppDetailService appDetailService,
                            RedisTemplate<String, Object> redisTemplate) {
        this.userService = userService;
        this.appDetailService = appDetailService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String authorize(OauthRequestVO.AuthorizeVO authorizeVO) {
        // 根据appId userName/email去查询数据库用户信息
        String appId = authorizeVO.getAppId();
        SsoUser ssoUser = Optional.ofNullable(userService.getSsoUserByUserNameOrEmail(appId, authorizeVO.getUserNameOrEmail()))
                .orElseThrow(() -> new BizException(ResponseCodeEnum.USER_NAME_OR_PASSWORD_ERROR));
        // 校验密码是否正确,之后可以增加用户状态校验
        if (Boolean.FALSE.equals(userService.assertPasswordEquals(ssoUser.getPassword(), authorizeVO.getPassword()))) {
            throw new BizException(ResponseCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        // 生成验证码和授权码并建立映射关系存入缓存中
        String code = UUID.randomUUID().toString().substring(0, 8);
        ssoUser.setPassword(null);
        // redis中添加code和token映射,验证码有效时间设置为2分钟
        redisTemplate.opsForValue().set(RedisKeyDict.SSO_CODE.getPrefix() + code, ssoUser, 2, TimeUnit.MINUTES);
        return String.format(FORMAT, ssoUser.getCallBackUrl(), "?code=", code);
    }

    @Override
    public String accessToken(OauthRequestVO.AccessTokenVO accessTokenVO) {
        // 用appId查询出来的appSecret与请求的appSecret比较
        if (!Objects.equals(appDetailService.getAppInfoByAppId(accessTokenVO.getAppId()).getAppSecret(), accessTokenVO.getAppSecret())) {
            throw new BizException(ResponseCodeEnum.APP_ID_OR_SECRET_ERROR);
        }
        SsoUser ssoUser = (SsoUser) redisTemplate.opsForValue().get(RedisKeyDict.SSO_CODE.getPrefix() + accessTokenVO.getCode());
        String newToken;
        if (ssoUser == null) {
            throw new BizException(ResponseCodeEnum.CODE_ID_INVALID);
        } else {
            // 设置token时间为24小时
            newToken = UUID.randomUUID().toString();
            ssoUser.setToken(newToken);
            // 删除该用户之前登录生成的token,还有种做法是将token存在数据库中或者验证码时间设置成和token一样长
            // 1.查出旧token
            String oldToken = (String) redisTemplate.opsForValue().get(RedisKeyDict.SSO_TOKEN_USER_ID.getPrefix() + ssoUser.getUserId());
            if (!StringUtils.isEmpty(oldToken)) {
                // 2.删除旧token
                redisTemplate.delete(RedisKeyDict.SSO_TOKEN.getPrefix() + oldToken);
            }
            // 3.存入新token
            redisTemplate.opsForValue().set(RedisKeyDict.SSO_TOKEN.getPrefix() + newToken, ssoUser, ssoUser.getAccessTokenValidTime(), TimeUnit.HOURS);
            // 4.让1可以查到旧token
            redisTemplate.opsForValue().set(RedisKeyDict.SSO_TOKEN_USER_ID.getPrefix() + ssoUser.getUserId(), newToken, ssoUser.getAccessTokenValidTime(), TimeUnit.HOURS);
        }
        return newToken;
    }

    @Override
    public SsoUser getSsoUserInfo(String token) {
        return (SsoUser) redisTemplate.opsForValue().get(RedisKeyDict.SSO_TOKEN.getPrefix() + token);
    }
}
