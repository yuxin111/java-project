package com.example.demo.shiro.mapper;

import com.example.demo.shiro.entity.SysToken;
import com.example.demo.shiro.entity.SysUser;
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

    /**
     * 用户信息 <- 用户名
     * @param loginName 登录名
     * @return 用户信息
     */
    public SysUser selectUserByLoginName(String loginName);

    /**
     * 用户信息 <- 用户id
     * @param userId 用户id
     * @return
     */
    public SysUser selectUserByUserId(Long userId);


}