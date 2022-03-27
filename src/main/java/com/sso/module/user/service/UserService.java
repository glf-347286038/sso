package com.sso.module.user.service;

import com.sso.module.oauth.model.SsoUser;
import com.sso.module.user.model.vo.UserRequestVO;
import com.sso.module.user.model.vo.UserResponseVO;

import java.util.List;

/**
 * @Author: golf
 * @Date: 2022/3/26 19:21
 */
public interface UserService {
    /**
     * 添加用户信息
     *
     * @param addUserVO 用户信息
     */
    void addUser(UserRequestVO.AddUserVO addUserVO);

    /**
     * 根据主键删除
     *
     * @param id 主键
     */
    void deleteById(Integer id);

    /**
     * 修改用户信息
     *
     * @param id 主键
     */
    void updateById(Integer id, UserRequestVO.UpdateUserVO updateUserVO);

    /**
     * 根据主键获取用户信息
     *
     * @param id 主键
     * @return 用户信息
     */
    UserResponseVO getUserInfoById(Integer id);

    /**
     * 用户列表查询
     *
     * @param queryUserVO 查询条件
     * @return 用户信息列表
     */
    List<UserResponseVO> listUser(UserRequestVO.QueryUserVO queryUserVO);

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
     * 根据appId  + 用户名/邮箱 查询出用户信息
     *
     * @param appId           appId
     * @param userNameOrEmail 用户名/邮箱
     * @return 用户信息
     */
    SsoUser getSsoUserByUserNameOrEmail(String appId, String userNameOrEmail);

    /**
     * 判断密码是否正确
     *
     * @param expected 期望值
     * @param actual   实际值
     * @return 是否相同
     */
    Boolean assertPasswordEquals(String expected, String actual);
}
