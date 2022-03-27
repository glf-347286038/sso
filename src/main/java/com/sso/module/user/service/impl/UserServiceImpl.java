package com.sso.module.user.service.impl;

import com.sso.common.exception.BizException;
import com.sso.common.exception.ResponseCodeEnum;
import com.sso.common.util.Md5Util;
import com.sso.module.user.mapper.RelUserAppDetailMapper;
import com.sso.module.user.mapper.UserMapper;
import com.sso.module.user.model.RelUserAppDetail;
import com.sso.module.user.model.User;
import com.sso.module.user.model.vo.UserRequestVO;
import com.sso.module.user.model.vo.UserResponseVO;
import com.sso.module.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

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
    public void addUser(UserRequestVO.AddUserVO addUserVO) {
        User userDO = User.builder()
                .userName(addUserVO.getUserName())
                .email(addUserVO.getEmail())
                .password(Md5Util.getMd5("05-13", addUserVO.getPassword()))
                .build();
        userMapper.insert(userDO);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateById(Integer id, UserRequestVO.UpdateUserVO updateUserVO) {
        User userDO = User.builder()
                .id(id)
                .build();
        String userName = updateUserVO.getUserName();
        String password = updateUserVO.getPassword();
        String email = updateUserVO.getEmail();
        if (userName != null && userName.isEmpty()) {
            throw new BizException(ResponseCodeEnum.PARAM_INVALID.getCode(), "用户名不能为空");
        } else {
            userDO.setUserName(userName);
        }
        if (!StringUtils.isEmpty(password) && password.length() < 6) {
            throw new BizException(ResponseCodeEnum.PARAM_INVALID.getCode(), "密码长度不得小于6");
        } else {
            userDO.setPassword(Md5Util.getMd5("05-13", password));
        }
        if (!StringUtils.isEmpty(email)) {
            userDO.setEmail(email);
        }
        userMapper.updateById(userDO);
    }

    @Override
    public UserResponseVO getUserInfoById(Integer id) {
        User userVO = userMapper.selectById(id);
        if (userVO == null) {
            return null;
        }
        return UserResponseVO.builder()
                .id(userVO.getId())
                .userName(userVO.getUserName())
                .email(userVO.getEmail())
                .createTime(userVO.getCreateTime())
                .build();
    }

    @Override
    public List<UserResponseVO> listUser(UserRequestVO.QueryUserVO queryUserVO) {
        return userMapper.listUser(queryUserVO);
    }

    @Override
    @Transactional
    public void addUserAppAuth(UserRequestVO.AddUserAppAuthVO userAppAuthVO) {
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
    public void deleteUserAppAuth(List<Integer> relUserAppDetailIds) {
        // 可以改为批量插入
        relUserAppDetailMapper.deleteBatchIds(relUserAppDetailIds);
    }
}
