package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserRoleMapper {
    /**
     * 删除用户角色数据 <- 用户id
     * @param userId 用户id
     * @return
     */
    public int deleteByUserId(Long userId);
}
