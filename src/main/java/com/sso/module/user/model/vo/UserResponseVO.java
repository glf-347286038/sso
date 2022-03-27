package com.sso.module.user.model.vo;

import com.sso.module.app.model.AppDetail;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: golf
 * @Date: 2022/3/27 2:42
 */
@Data
public class UserResponseVO {
    private Integer id;
    private String userName;
    private String email;
    private Date createTime;
    /**
     * 有权限的app信息列表  id, user_name, email, status, create_time
     */
    List<AppDetail> appDetails;
}
