package com.example.demo.core.service.impl;

import com.example.demo.common.utils.SecurityUtils;
import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.mapper.SysUserMapper;
import com.example.demo.core.mapper.SysUserRoleMapper;
import com.example.demo.core.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public SysUser selectUserByLoginName(String loginName) {
        return userMapper.selectUserByLoginName(loginName);
    }

    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public int addUser(SysUser user) {
        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(SecurityUtils.md5(user.getPassword()));
        }else{
            throw new MyException("请输入密码");
        }

        SysUser tempUser = userMapper.selectUserByLoginName(user.getLoginName());
        if(tempUser != null){
            throw new MyException("登录账号不能重复");
        }

        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(SysUser user) {
        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(SecurityUtils.md5(user.getPassword()));
        }

        List<SysUser> userList;
        SysUser onceUser = new SysUser();
        onceUser.setUserId(user.getUserId());
        onceUser.setLoginName(user.getLoginName());
        userList = userMapper.selectUserByParams(onceUser);
        if(userList != null && userList.size() > 0){
            throw new MyException("登录账号不能重复");
        }

        return userMapper.updateUser(user);
    }

    @Override
    @Transactional
    public int deleteUserById(Long userId) {
        // 删除用户关联角色
        userRoleMapper.deleteByUserId(userId);
        return userMapper.deleteUserById(userId);
    }
}
