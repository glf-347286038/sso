package com.sso.module.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 *
 * @author golf
 */
@Data
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 7262710608638763976L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码md5
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态0-禁用,1-启用
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private Integer createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人id
     */
    private Integer updateBy;
}
