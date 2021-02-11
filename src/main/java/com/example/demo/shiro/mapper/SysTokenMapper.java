package com.example.demo.shiro.mapper;

import com.example.demo.shiro.entity.SysToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
