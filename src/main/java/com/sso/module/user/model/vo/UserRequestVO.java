package com.sso.module.user.model.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: golf
 * @Date: 2022/3/27 2:03
 */
@Data
public class UserRequestVO {

    private final QueryUserVO queryUserVO;
    private final AddUserVO addUserVO;
    private final UpdateUserVO updateUserVO;
    private final UserAppAuthVO userAppAuthVO;

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

    @Data
    public static class UserAppAuthVO {
        /**
         * 用户id
         */
        @Min(value = 1, message = "用户id不合法")
        private Integer userId;
        /**
         * appDetailId
         */
        @NotNull(message = "appId不能为空")
        private List<Integer> appDetailIds;
    }
}
