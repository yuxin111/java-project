package com.example.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public String add(int id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        int result = userMapper.add(user);
        return result+"";
    }
}
