package com.example.demo.mapper;

import com.example.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper {
    /**
     * 查找用户列表
     * @param sysUser 用户信息
     * @return 用户信息列表
     */
    public List<SysUser> selectUserList(SysUser sysUser);
}
