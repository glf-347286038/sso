package com.sso.module.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.module.app.model.AppDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppDetailMapper extends BaseMapper<AppDetail> {

    List<AppDetail> list();
}
