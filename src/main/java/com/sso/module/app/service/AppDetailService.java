package com.sso.module.app.service;

import com.sso.module.app.model.AppDetail;

/**
 * @Author: golf
 * @Date: 2022/3/25 1:31
 */
public interface AppDetailService {
    /**
     * 添加三方app信息
     *
     * @param appDetail 三方app信息
     */
    void addAppDetail(AppDetail appDetail);
}
