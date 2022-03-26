package com.sso.module.user.controller;

import com.sso.common.ResponseData;
import com.sso.module.user.model.vo.UserRequestVO;
import com.sso.module.user.model.vo.UserResponseVO;
import com.sso.module.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: golf
 * @Date: 2022/3/27 1:57
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseData<String> add(@Validated @RequestBody UserRequestVO.AddUserVO addUserVO) {
        userService.addUser(addUserVO);
        return ResponseData.success("新增用户成功");
    }

    @DeleteMapping("/{id}")
    public ResponseData<String> delete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return ResponseData.success("删除用户成功");
    }

    @PutMapping("/{id}")
    public ResponseData<String> update(@PathVariable("id") Integer id, @RequestBody UserRequestVO.UpdateUserVO updateUserVO) {
        userService.updateById(id, updateUserVO);
        return ResponseData.success("修改用户信息成功");
    }

    @GetMapping("/{id}")
    public ResponseData<UserResponseVO> getUserInfo(@PathVariable("id") Integer id) {
        return ResponseData.success(userService.getUserInfoById(id));
    }

    @GetMapping("/list")
    public ResponseData<List<UserResponseVO>> listUser(String userName) {
        UserRequestVO.QueryUserVO queryUserVO = new UserRequestVO.QueryUserVO();
        queryUserVO.setUserName(userName);
        return ResponseData.success(userService.listUser(queryUserVO));
    }
}
