package com.sso.module.oauth.controller;

import com.sso.common.ResponseData;
import com.sso.module.oauth.model.SsoUser;
import com.sso.module.oauth.model.vo.OauthRequestVO;
import com.sso.module.oauth.service.OauthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: golf
 * @Date: 2022/2/27 20:51
 */
@RequestMapping("oauth")
@RestController
public class OauthController {
    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    /**
     * 获取验证码接口
     *
     * @param authorizeVO 用户名、密码、appId
     * @return 验证码
     */
    @PostMapping("/authorize")
    public ResponseData<String> authorize(@Validated @RequestBody OauthRequestVO.AuthorizeVO authorizeVO) {
        return ResponseData.success(oauthService.authorize(authorizeVO));
    }

    /**
     * 根据验证码获取授权吗
     *
     * @param accessTokenVO appId、appSecret、验证码
     * @return 授权码
     */
    @PostMapping("/access/token")
    public ResponseData<String> accessToken(@Validated @RequestBody OauthRequestVO.AccessTokenVO accessTokenVO) {
        return ResponseData.success(oauthService.accessToken(accessTokenVO));
    }

    @GetMapping("getSsoUserInfo")
    public ResponseData<SsoUser> getSsoUserInfo(@RequestParam String token) {
        return ResponseData.success(oauthService.getSsoUserInfo(token));
    }

}
