package com.example.demo.shiro.service;

import com.example.demo.shiro.entity.SysToken;
import com.example.demo.shiro.entity.SysUser;

import java.util.Map;

public interface IShiroService {

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

    /**
     * 生成一个token
     * @param userId 用户id
     * @return
     */
    public Map<String,Object> createToken(Long userId);

    /**
     * token信息 <- token
     * @param token token字符串
     * @return
     */
    public SysToken selectTokenByToken(String token);

}
