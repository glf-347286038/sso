package com.sso.module.app.model.vo;

import com.sso.module.app.model.AppDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * app_detail
 *
 * @author golf
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppDetailVO extends AppDetail {
    private static final long serialVersionUID = 6716054555515347003L;
    /**
     * 创建人姓名
     */
    private String createUserName;
}
