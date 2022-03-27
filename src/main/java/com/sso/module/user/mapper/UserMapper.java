package com.sso.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.module.oauth.model.SsoUser;
import com.sso.module.user.model.User;
import com.sso.module.user.model.vo.UserRequestVO;
import com.sso.module.user.model.vo.UserResponseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<UserResponseVO> listUser(@Param("queryUserVO") UserRequestVO.QueryUserVO queryUserVO);

    /**
     * @param queryUserVO 查询参数
     * @return 用户信息
     */
    SsoUser getUserByCondition(@Param("queryUserVO") UserRequestVO.QueryUserVO queryUserVO);
}
