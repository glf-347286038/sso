package com.sso.module.app.service.impl;

import com.sso.module.app.mapper.AppDetailMapper;
import com.sso.module.app.model.AppDetail;
import com.sso.module.app.service.AppDetailService;
import org.springframework.stereotype.Service;

/**
 * @Author: golf
 * @Date: 2022/3/25 1:31
 */
@Service
public class AppDetailServiceImpl implements AppDetailService {
    private final AppDetailMapper appDetailMapper;

    public AppDetailServiceImpl(AppDetailMapper appDetailMapper) {
        this.appDetailMapper = appDetailMapper;
    }

    @Override
    public void addAppDetail(AppDetail appDetail) {
        appDetailMapper.insert(appDetail);
    }
}
