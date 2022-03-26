package com.sso.module.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * rel_user_app_detail
 *
 * @author golf
 */
@Data
public class RelUserAppDetail implements Serializable {
    private static final long serialVersionUID = 2394731807624055420L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * user表id
     */
    private Integer userId;

    /**
     * app_detail表id
     */
    private Integer appDetailId;
}
