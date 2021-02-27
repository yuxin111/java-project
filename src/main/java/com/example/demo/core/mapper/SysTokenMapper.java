package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysTokenMapper{

    /**
     * token信息 <- 用户id
     * @param userId 用户id
     * @return token信息
     */
    public SysToken selectTokenByUserId(Long userId);

    /**
     * 保存token
     * @param sysToken token
     * @return
     */
    public int insertToken(SysToken sysToken);

    /**
     * token信息 <- token
     * @param token token字符串
     * @return
     */
    public SysToken selectTokenByToken(String token);
}
