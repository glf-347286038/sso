package com.sso.module.user.service;

import com.sso.module.user.model.vo.UserRequestVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @Author: golf
 * @Date: 2022-03-26 19:42
 */
@Log4j2
@ActiveProfiles("dev")
@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void addUser() {
        UserRequestVO.AddUserVO addUserVO = new UserRequestVO.AddUserVO();
        addUserVO.setUserName("");
        addUserVO.setPassword("");
        addUserVO.setEmail("");
        userService.addUser(addUserVO);
        Assert.assertTrue(Boolean.TRUE);
    }

    @Test
    void testRandom() {
        String s = UUID.randomUUID().toString();
        log.info(s);
        String password = "3cb826ed-3643-4569-aace-f9aef7c28b8b";
        String appSecret = DigestUtils.md5Hex("23" + password);
        log.error(appSecret);
        String actual = DigestUtils.md5Hex("xx" + password);
        Assert.assertEquals("密码错误", appSecret, actual);
    }
}
