package com.example.demo;

import com.example.demo.core.entity.SysUser;
import org.joda.time.field.PreciseDurationDateTimeField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/10/31
 **/
@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redistest(){
        redisTemplate.opsForValue().set("name","yuxin");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    public void redistest2(){
        SysUser user = new SysUser();
        user.setUserId(1L);
        user.setLoginName("yuxin");
        redisTemplate.opsForValue().set("user",user);
        SysUser user1 = (SysUser) redisTemplate.opsForValue().get("user");
        System.out.println(user1.getLoginName());
    }
}
