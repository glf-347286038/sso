package com.sso.module.rel.user.app.service;

import com.sso.module.app.model.AppDetail;
import com.sso.module.user.model.vo.UserRequestVO;

import java.util.List;

/**
 * @Author: golf
 * @Date: 2022/3/27 14:51
 */
public interface RelUserAppDetailService {
    /**
     * 添加用户的app权限
     *
     * @param userAppAuthVO 用户和app信息
     */
    void addUserAppAuth(UserRequestVO.UserAppAuthVO userAppAuthVO);

    /**
     * 删除用户的app权限
     *
     * @param userId       用户id
     * @param appDetailIds appDetailId列表
     */
    void deleteUserAppAuth(Integer userId, List<Integer> appDetailIds);

    /**
     * 根据用户id查询出有权限的app信息
     *
     * @param userId 用户表主键id
     * @return app列表
     */
    List<AppDetail> listAppDetailByUserId(Integer userId);
}
