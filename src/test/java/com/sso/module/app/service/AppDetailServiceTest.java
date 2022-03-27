package com.sso.module.app.service;

import com.sso.module.app.model.AppDetail;
import com.sso.module.oauth.model.SsoUser;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: golf
 * @Date: 2022/3/25 1:57
 */
@Log4j2
@ActiveProfiles("dev")
@SpringBootTest
@RunWith(SpringRunner.class)
class AppDetailServiceTest {
    @Autowired
    private AppDetailService appDetailService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    @Transactional
    void addAppDetail() {
        AppDetail appDetail = new AppDetail();
        appDetail.setAppId("delta-test");
        String secret = "gaolingfeng";
        appDetail.setAppSecret(DigestUtils.md5Hex(secret));
        appDetailService.addAppDetail(appDetail);
        Assert.assertTrue(Boolean.TRUE);
    }

    @Test
    void testRedis() {
        SsoUser ssoUser = new SsoUser();
        ssoUser.setAppId("222");
        ssoUser.setPassword("glf");
        ssoUser.setCallBackUrl("123");
        redisTemplate.opsForValue().set("user", ssoUser);
        SsoUser user = (SsoUser) redisTemplate.opsForValue().get("user");
        Assert.assertTrue(Boolean.TRUE);
    }
}
