package com.sso.module.rel.user.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sso.common.exception.BizException;
import com.sso.common.exception.ResponseCodeEnum;
import com.sso.module.app.mapper.AppDetailMapper;
import com.sso.module.app.model.AppDetail;
import com.sso.module.rel.user.app.model.RelUserAppDetail;
import com.sso.module.rel.user.app.service.RelUserAppDetailService;
import com.sso.module.user.mapper.RelUserAppDetailMapper;
import com.sso.module.user.model.vo.UserRequestVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: golf
 * @Date: 2022/3/27 14:51
 */
@Service
public class RelUserAppDetailImpl implements RelUserAppDetailService {
    private final RelUserAppDetailMapper relUserAppDetailMapper;
    private final AppDetailMapper appDetailMapper;

    public RelUserAppDetailImpl(RelUserAppDetailMapper relUserAppDetailMapper, AppDetailMapper appDetailMapper) {
        this.relUserAppDetailMapper = relUserAppDetailMapper;
        this.appDetailMapper = appDetailMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void addUserAppAuth(UserRequestVO.UserAppAuthVO userAppAuthVO) {
        if (CollectionUtils.isEmpty(userAppAuthVO.getAppDetailIds())) {
            throw new BizException(ResponseCodeEnum.PARAM_INVALID.getCode(), "appId为空");
        }
        // 可以改为批量插入
        for (Integer appDetailId : userAppAuthVO.getAppDetailIds()) {
            relUserAppDetailMapper.insert(RelUserAppDetail.builder()
                    .userId(userAppAuthVO.getUserId())
                    .appDetailId(appDetailId)
                    .build());
        }
    }

    @Override
    public void deleteUserAppAuth(Integer userId, List<Integer> appDetailIds) {
        QueryWrapper<RelUserAppDetail> relUserAppDetailQueryWrapper = new QueryWrapper<>();
        relUserAppDetailQueryWrapper.eq("user_id", userId);
        relUserAppDetailQueryWrapper.in("app_detail_id", appDetailIds);
        relUserAppDetailMapper.delete(relUserAppDetailQueryWrapper);
    }

    @Override
    public List<AppDetail> listAppDetailByUserId(Integer userId) {
        // 查询关联的appDetailId
        QueryWrapper<RelUserAppDetail> relUserAppDetailQueryWrapper = new QueryWrapper<>();
        relUserAppDetailQueryWrapper.eq("user_id", userId);
        List<Integer> appDetailIds = relUserAppDetailMapper.selectList(relUserAppDetailQueryWrapper)
                .stream().map(RelUserAppDetail::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(appDetailIds)) {
            return Collections.emptyList();
        }
        // 根据appDetailIds查找appDetail信息
        QueryWrapper<AppDetail> appDetailQueryWrapper = new QueryWrapper<>();
        appDetailQueryWrapper.in("id", appDetailIds);
        return appDetailMapper.selectList(appDetailQueryWrapper);
    }
}
