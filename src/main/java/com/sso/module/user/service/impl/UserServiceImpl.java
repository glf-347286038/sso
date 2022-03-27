package com.sso.module.user.service.impl;

import com.sso.common.exception.BizException;
import com.sso.common.exception.ResponseCodeEnum;
import com.sso.common.util.Md5Util;
import com.sso.common.util.RegExpValidatorUtil;
import com.sso.module.oauth.model.SsoUser;
import com.sso.module.rel.user.app.service.RelUserAppDetailService;
import com.sso.module.user.mapper.UserMapper;
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
import java.util.Objects;

/**
 * @Author: golf
 * @Date: 2022-03-26 19:21
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private static final String USER_PASSWORD_SALT = "05-13";

    private final UserMapper userMapper;
    private final RelUserAppDetailService relUserAppDetailService;

    public UserServiceImpl(UserMapper userMapper, RelUserAppDetailService relUserAppDetailService) {
        this.userMapper = userMapper;
        this.relUserAppDetailService = relUserAppDetailService;
    }

    @Override
    public void addUser(UserRequestVO.AddUserVO addUserVO) {
        User userDO = User.builder()
                .userName(addUserVO.getUserName())
                .email(addUserVO.getEmail())
                .password(Md5Util.getMd5(USER_PASSWORD_SALT, addUserVO.getPassword()))
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
            userDO.setPassword(Md5Util.getMd5(USER_PASSWORD_SALT, password));
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
        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setId(userVO.getId());
        userResponseVO.setUserName(userVO.getUserName());
        userResponseVO.setEmail(userVO.getEmail());
        userResponseVO.setCreateTime(userVO.getCreateTime());
        userResponseVO.setAppDetails(relUserAppDetailService.listAppDetailByUserId(id));
        return userResponseVO;
    }

    @Override
    public List<UserResponseVO> listUser(UserRequestVO.QueryUserVO queryUserVO) {
        return userMapper.listUser(queryUserVO);
    }

    @Override
    @Transactional
    public void addUserAppAuth(UserRequestVO.UserAppAuthVO userAppAuthVO) {
        relUserAppDetailService.addUserAppAuth(userAppAuthVO);
    }

    @Override
    public void deleteUserAppAuth(Integer userId, List<Integer> appDetailIds) {
        if (CollectionUtils.isEmpty(appDetailIds)) {
            throw new BizException(ResponseCodeEnum.PARAM_INVALID.getCode(), "请选择要删除的app");
        }
        relUserAppDetailService.deleteUserAppAuth(userId, appDetailIds);
    }

    @Override
    public SsoUser getSsoUserByUserNameOrEmail(String appId, String userNameOrEmail) {
        UserRequestVO.QueryUserVO queryUserDO = new UserRequestVO.QueryUserVO();
        // 判断格式 用户名还是邮箱
        boolean emailFlag = RegExpValidatorUtil.checkEmail(userNameOrEmail);
        queryUserDO.setAppId(appId);
        if (emailFlag) {
            queryUserDO.setEmail(userNameOrEmail);
        } else {
            queryUserDO.setUserName(userNameOrEmail);
        }
        // 从数据库中查找 后续可以先从缓存中查找
        return userMapper.getUserByCondition(queryUserDO);
    }

    @Override
    public Boolean assertPasswordEquals(String expected, String actual) {
        actual = Md5Util.getMd5(USER_PASSWORD_SALT, actual);
        return Objects.equals(expected, actual);
    }
}
