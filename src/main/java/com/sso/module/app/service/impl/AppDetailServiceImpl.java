package com.sso.module.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.sso.common.util.Md5Util;
import com.sso.module.app.mapper.AppDetailMapper;
import com.sso.module.app.model.AppDetail;
import com.sso.module.app.model.vo.AppDetailRequestVO;
import com.sso.module.app.service.AppDetailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: golf
 * @Date: 2022/3/25 1:31
 */
@Service
@Log4j2
public class AppDetailServiceImpl implements AppDetailService {
    private final AppDetailMapper appDetailMapper;

    public AppDetailServiceImpl(AppDetailMapper appDetailMapper) {
        this.appDetailMapper = appDetailMapper;
    }

    @Override
    public void addAppDetail(AppDetail appDetail) {
        log.info("添加第三方appId入参:{}", JSON.toJSONString(appDetail));
        // 将appSecret加盐再进行MD5存入数据库中
        appDetail.setAppSecret(Md5Util.getMd5("1997", appDetail.getAppSecret()));
        appDetailMapper.insert(appDetail);
    }

    @Override
    public void deleteAppDetail(Integer appDetailIds) {
        appDetailMapper.deleteById(appDetailIds);
    }

    @Override
    public void updateAppDetail(Integer id, AppDetailRequestVO.UpdateAppVO updateAppVO) {
        AppDetail appDetailDO = AppDetail.builder()
                .id(id)
                .appId(updateAppVO.getAppId())
                .appName(updateAppVO.getAppName())
                .appSecret(Md5Util.getMd5("1997", updateAppVO.getAppSecret()))
                .webServerRedirectUri(updateAppVO.getWebServerRedirectUri())
                .accessTokenValidTime(updateAppVO.getAccessTokenValidTime())
                .build();
        appDetailMapper.updateById(appDetailDO);
    }

    @Override
    public AppDetail getAppInfo(Integer id) {
        AppDetail appDetail = appDetailMapper.selectById(id);
        // 密码不展示,后期可以做统一脱敏
        if (appDetail != null) {
            appDetail.setAppSecret(null);
        }
        return appDetailMapper.selectById(id);
    }

    @Override
    public List<AppDetail> listAppDetail() {
        return appDetailMapper.list();
    }
}
