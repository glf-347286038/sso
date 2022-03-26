package com.sso.module.user.service.impl;

import com.sso.module.user.mapper.RelUserAppDetailMapper;
import com.sso.module.user.mapper.UserMapper;
import com.sso.module.user.model.User;
import com.sso.module.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @Author: golf
 * @Date: 2022-03-26 19:21
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RelUserAppDetailMapper relUserAppDetailMapper;

    public UserServiceImpl(UserMapper userMapper, RelUserAppDetailMapper relUserAppDetailMapper) {
        this.userMapper = userMapper;
        this.relUserAppDetailMapper = relUserAppDetailMapper;
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }
}
