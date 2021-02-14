package com.example.demo.shiro.service.impl;

import com.example.demo.shiro.entity.SysToken;
import com.example.demo.shiro.entity.SysUser;
import com.example.demo.shiro.mapper.SysTokenMapper;
import com.example.demo.shiro.mapper.SysUserMapper;
import com.example.demo.shiro.realm.TokenGenerator;
import com.example.demo.shiro.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShiroServiceImpl implements IShiroService {

    // token过期时间（小时）
    private final static int EXPIRE = 24;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysTokenMapper sysTokenMapper;

    @Override
    public SysUser selectUserByLoginName(String loginName) {
        return sysUserMapper.selectUserByLoginName(loginName);
    }

    @Override
    public SysUser selectUserByUserId(Long userId) {
        return sysUserMapper.selectUserByUserId(userId);
    }

    @Override
    public Map<String, Object> createToken(Long userId) {
        Map<String, Object> result = new HashMap<>();
        String token = TokenGenerator.generateValue();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusHours(EXPIRE);

        //判断是否生成过token
        SysToken tokenEntity = sysTokenMapper.selectTokenByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysToken();
            tokenEntity.setUserId(userId);
        }
        tokenEntity.setToken(token);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(expireTime);

        sysTokenMapper.insertToken(tokenEntity);

        result.put("token", token);
        result.put("expire", expireTime);
        return result;
    }

    @Override
    public SysToken selectTokenByToken(String token) {
        return sysTokenMapper.selectTokenByToken(token);
    }
}
