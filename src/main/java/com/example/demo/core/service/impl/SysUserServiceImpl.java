package com.example.demo.core.service.impl;

import com.example.demo.core.entity.SysUser;
import com.example.demo.core.mapper.SysUserMapper;
import com.example.demo.core.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    SysUserMapper userMapper;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public SysUser selectUserByLoginName(String loginName) {
        return userMapper.selectUserByLoginName(loginName);
    }

    @Override
    public SysUser selectUserByUserId(Long userId) {
        return userMapper.selectUserByUserId(userId);
    }
}
