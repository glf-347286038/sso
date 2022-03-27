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
    void addUserAppAuth(UserRequestVO.AddUserAppAuthVO userAppAuthVO);

    /**
     * 删除用户的app权限
     *
     * @param relUserAppDetailIds 用户和app关联的主键id
     */
    void deleteUserAppAuth(List<Integer> relUserAppDetailIds);

    /**
     * 根据用户id查询出有权限的app信息
     *
     * @return app列表
     */
    List<AppDetail> listAppDetailByUserId(Integer userId);
}
