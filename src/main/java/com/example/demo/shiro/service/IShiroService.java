package com.example.demo.shiro.service;

import com.example.demo.shiro.entity.SysToken;
import com.example.demo.shiro.entity.SysUser;

import java.util.Map;

public interface IShiroService {

    /**
     * 用户信息 <- 用户名
     * @param loginName 登录名
     * @return 用户信息
     */
    public SysUser selectUserByLoginName(String loginName);

    /**
     *  生成一个token
     * @param userId 用户id
     * @return
     */
    public Map<String,Object> createToken(Long userId);


}
