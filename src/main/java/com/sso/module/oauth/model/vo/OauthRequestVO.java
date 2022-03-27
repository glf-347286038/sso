package com.sso.module.oauth.model.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author: golf
 * @Date: 2022/3/27 17:09
 */
@Data
public class OauthRequestVO {

    private final AuthorizeVO authorizeVO;
    private final AccessTokenVO accessTokenVO;


    /**
     * 获取验证码VO
     */
    @Data
    public static class AuthorizeVO {
        @NotBlank(message = "appId不能为空")
        @Length(min = 36, max = 36, message = "appId非法")
        private String appId;

        /**
         * 用户名或邮箱
         */
        @NotBlank(message = "请输入用户名或邮箱")
        private String userNameOrEmail;

        @NotBlank(message = "请输入密码")
        @Length(min = 6, message = "密码长度不得小于6")
        private String password;

    }

    /**
     * 获取授权码VO
     */
    @Data
    public static class AccessTokenVO {
        @NotBlank(message = "appId不能为空")
        @Length(min = 36, max = 36, message = "appId非法")
        private String appId;

        @NotBlank(message = "appSecret不能为空")
        private String appSecret;

        @NotBlank(message = "验证码不能为空")
        @Length(min = 8, max = 8, message = "code非法")
        private String code;
    }

}
