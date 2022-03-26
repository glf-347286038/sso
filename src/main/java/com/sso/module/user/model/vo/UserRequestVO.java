package com.sso.module.user.model.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: golf
 * @Date: 2022/3/27 2:03
 */
@Data
public class UserRequestVO {

    private final QueryUserVO queryUserVO;
    private final AddUserVO addUserVO;
    private final UpdateUserVO updateUserVO;

    @Data
    public static class QueryUserVO {
        private String userName;
    }

    @Data
    public static class AddUserVO {
        @Length(min = 1, max = 30, message = "用户名不合法")
        private String userName;
        @Length(min = 6, message = "密码长度不得小于6")
        private String password;
        @Length(min = 1, max = 50, message = "邮箱长度不合法")
        private String email;
    }

    @Data
    public static class UpdateUserVO {
        private String userName;
        private String password;
        private String email;
    }
}
