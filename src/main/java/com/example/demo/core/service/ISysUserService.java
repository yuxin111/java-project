package com.example.demo.core.service;

import com.example.demo.core.entity.SysToken;
import com.example.demo.core.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface ISysUserService {

    /**
     * 用户列表 <- 分页
     *
     * @param user 用户信息
     * @return
     */
    public List<SysUser> selectUserList(SysUser user);

    /**
     * 用户信息 <- 用户名
     * @param loginName 登录名
     * @return
     */
    public SysUser selectUserByLoginName(String loginName);

    /**
     * 用户信息 <- 用户id
     * @param userId 用户id
     * @return
     */
    public SysUser selectUserByUserId(Long userId);

}
