package com.sso.oauth.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author: golf
 * @Date: 2022/3/21 20:09
 */
@Data
public class OauthDTO {
    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度超过50个字符")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "appId不能为空")
    private String appId;
}
