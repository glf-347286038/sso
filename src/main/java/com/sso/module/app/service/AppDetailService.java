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

    /**
     * 根据appId查询appDetail信息
     *
     * @param appId appId
     * @return appDetail信息
     */
    AppDetail getAppInfoByAppId(String appId);

    /**
     * 断言用户传入的appSecret与期望值是否一致
     *
     * @param expected 期望值
     * @param actual   实际值
     * @return 是否一致
     */
    Boolean assertAppSecretEquals(String expected, String actual);
}
