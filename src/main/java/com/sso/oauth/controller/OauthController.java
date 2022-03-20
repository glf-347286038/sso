package com.sso.oauth.controller;

import com.sso.common.ResponseData;
import com.sso.oauth.pojo.SsoUser;
import com.sso.oauth.service.OauthService;
import lombok.SneakyThrows;
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
     * @param userName 用户名
     * @param password 密码
     * @param appId    三方appId
     * @return 验证码
     */
    @SneakyThrows
    @PostMapping("/authorize")
    public ResponseData<String> authorize(@RequestParam String userName,
                                          @RequestParam String password,
                                          @RequestParam String appId) {
        // 基本参数校验appId是否合法
        // 校验用户名密码
        return ResponseData.success(oauthService.authorize(userName, password, appId));
    }

    /**
     * 根据验证码获取授权吗
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @param code      验证码
     * @return 授权码
     */
    @GetMapping("/access_token")
    public ResponseData<String> accessToken(@RequestParam String appId,
                                            @RequestParam String appSecret,
                                            @RequestParam String code) {
        return ResponseData.success(oauthService.accessToken(appId, appSecret, code));
    }

    @PostMapping("getSsoUserInfo")
    public ResponseData<SsoUser> getSsoUserInfo(@RequestParam String token) {
        return ResponseData.success(oauthService.getSsoUserInfo(token));
    }

}
