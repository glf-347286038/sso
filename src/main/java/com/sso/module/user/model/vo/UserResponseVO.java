package com.sso.module.user.model.vo;

import com.sso.module.app.model.AppDetail;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    /**
     * 有权限的app信息列表
     */
    List<AppDetail> appDetails;
}
