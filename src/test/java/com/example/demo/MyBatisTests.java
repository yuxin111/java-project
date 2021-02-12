package com.example.demo;

import com.example.demo.shiro.entity.SysToken;
import com.example.demo.shiro.entity.SysUser;
import com.example.demo.shiro.mapper.SysTokenMapper;
import com.example.demo.shiro.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@SpringBootTest
class MyBatisTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        connection.close();
    }

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysTokenMapper sysTokenMapper;

    @Test
    public void toTest(){
//        List<User> userList = userMapper.queryAll();
//        userList.forEach(e -> System.out.println(e));

//        List<SysUser> sysUserList;
//        SysUser sysUser = new SysUser();
//        Page<SysUser> pageInfo =
//                PageHelper
//                        .startPage(1,1)
//                        .doSelectPage(() -> sysUserMapper.selectUserList(sysUser));
//        System.out.println(pageInfo.toString());

        SysUser sysUser;
        sysUser = sysUserMapper.selectUserByLoginName("yuxin");
        System.out.println(sysUser.toString());
    }

    @Test
    public void testByToken(){
//        SysToken token = sysTokenMapper.selectTokenByUserId(1L);
//        System.out.println(token.toString());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusHours(24);
        SysToken sysToken = new SysToken();
        sysToken.setUserId(2L);
        sysToken.setToken("fc2d8ea6091bc551fb7a9016ce2f7acd");
        sysToken.setUpdateTime(now);
        sysToken.setExpireTime(expireTime);
        sysTokenMapper.insertToken(sysToken);

    }

}
