package com.sso.module.app.model.vo;

import lombok.Data;

/**
 * app_detail
 *
 * @author golf
 */
@Data
public class AppDetailRequestVO {
    private static final long serialVersionUID = 6716054555515347003L;
    /**
     * 更新参数
     */
    private UpdateAppVO updateAppVO;

    @Data
    public static class UpdateAppVO {
        private String appId;
        private String appName;
        private String appSecret;
        private String webServerRedirectUri;
        private Integer accessTokenValidTime;
    }
}
