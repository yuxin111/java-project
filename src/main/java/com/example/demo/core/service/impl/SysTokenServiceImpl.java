package com.example.demo.core.service.impl;

import com.example.demo.core.entity.SysToken;
import com.example.demo.core.mapper.SysTokenMapper;
import com.example.demo.core.service.ISysTokenService;
import com.example.demo.shiro.realm.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SysTokenServiceImpl implements ISysTokenService {

    // token过期时间（seconds）
    private final static int EXPIRE = 7 * 24 * 60 * 60;

    @Autowired
    SysTokenMapper sysTokenMapper;

    @Override
    public Map<String, Object> createToken(Long userId) {
        Map<String, Object> result = new HashMap<>();
        String token = TokenGenerator.generateValue();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusSeconds(EXPIRE);

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
        result.put("expire", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(expireTime));
        return result;
    }

    @Override
    public SysToken selectTokenByToken(String token) {
        return sysTokenMapper.selectTokenByToken(token);
    }
}
