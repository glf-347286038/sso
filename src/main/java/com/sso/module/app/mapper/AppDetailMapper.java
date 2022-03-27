package com.sso.module.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.module.app.model.AppDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppDetailMapper extends BaseMapper<AppDetail> {

    AppDetail getAppDetail(@Param("appId") String appId);

    List<AppDetail> list();
}
