package com.sso.module.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.module.app.model.AppDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author golf
 */
@Repository
public interface AppDetailMapper extends BaseMapper<AppDetail> {
    /**
     * 根据appId
     *
     * @param appId appId
     * @return 三方app详情
     */
    AppDetail getAppDetail(@Param("appId") String appId);

    /**
     * 查询三方app列表
     *
     * @return app列表
     */
    List<AppDetail> list();
}
