package com.sso.module.user.service;

import com.sso.module.user.model.User;

/**
 * @Author: golf
 * @Date: 2022/3/26 19:21
 */
public interface UserService {
    /**
     * 添加用户信息
     *
     * @param user 用户信息
     */
    void addUser(User user);
}
