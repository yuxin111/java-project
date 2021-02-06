package com.example.demo;

import com.example.demo.entity.SysUser;
import com.example.demo.entity.User;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
    UserMapper userMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void toTest(){
//        List<User> userList = userMapper.queryAll();
//        userList.forEach(e -> System.out.println(e));

        List<SysUser> sysUserList;
        SysUser sysUser = new SysUser();
        Page<SysUser> pageInfo =
                PageHelper
                        .startPage(2,1)
                        .doSelectPage(() -> sysUserMapper.selectUserList(sysUser));
        System.out.println(pageInfo.toString());
    }

}
