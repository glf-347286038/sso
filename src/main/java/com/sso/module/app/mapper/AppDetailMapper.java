package com.sso.module.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.module.app.model.AppDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDetailMapper extends BaseMapper<AppDetail> {

    AppDetail selectByPrimaryKey(Integer id);
}
