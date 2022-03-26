package com.sso.module.app.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * app_detail
 *
 * @author golf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @NotEmpty(message = "appId不允许为空")
    @Length(max = 20, message = "appId长度超过20")
    private String appId;

    /**
     * app密码md5
     */
    @NotEmpty(message = "app密码不允许为空")
    private String appSecret;

    /**
     * 客户端重定向URI
     */
    @NotEmpty(message = "重定向uri不允许为空")
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
