package com.sso.module.app.service;

import com.sso.module.app.model.AppDetail;
import com.sso.module.app.model.vo.AppDetailRequestVO;

import java.util.List;

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

    /**
     * 删除
     *
     * @param id 主键
     */
    void deleteAppDetail(Integer id);

    /**
     * 更新
     *
     * @param id          主键
     * @param updateAppVO 参数
     */
    void updateAppDetail(Integer id, AppDetailRequestVO.UpdateAppVO updateAppVO);

    /**
     * 查询
     *
     * @param id 主键
     * @return app详情
     */
    AppDetail getAppInfo(Integer id);

    /**
     * 列表查询
     *
     * @return app列表
     */
    List<AppDetail> listAppDetail();
}
