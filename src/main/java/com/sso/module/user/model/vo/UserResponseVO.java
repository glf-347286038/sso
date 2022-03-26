package com.sso.module.user.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: golf
 * @Date: 2022/3/27 2:42
 */
@Data
@Builder
public class UserResponseVO {
    private Integer id;
    private String userName;
    private String email;
    private Date createTime;
}
