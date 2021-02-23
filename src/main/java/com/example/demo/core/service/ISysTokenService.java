package com.example.demo.core.service;

import com.example.demo.core.entity.SysToken;
import com.example.demo.core.entity.SysUser;

import java.util.Map;

public interface ISysTokenService {

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
