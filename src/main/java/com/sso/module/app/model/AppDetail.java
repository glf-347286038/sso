package com.sso.module.app.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * app_detail
 *
 * @author golf
 */
@Data
public class AppDetail implements Serializable {
    private static final long serialVersionUID = -1587754718162428095L;
    /**
     * 主键,设置为自增 module
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * app_id
     */
    private String appId;

    /**
     * app密码md5
     */
    private String appSecret;

    /**
     * 客户端重定向URI
     */
    private String webServerRedirectUri;

    /**
     * token有效时间单位小时
     */
    private Integer accessTokenValidTime;

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
