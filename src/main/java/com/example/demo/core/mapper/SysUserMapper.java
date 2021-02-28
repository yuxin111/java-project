package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper {
    /**
     * 用户列表 <- 用户信息
     *
     * @param sysUser 用户信息
     * @return 用户信息列表
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 用户信息 <- 用户名
     *
     * @param loginName 用户名
     * @return 用户信息
     */
    public SysUser selectUserByLoginName(String loginName);

    /**
     * 用户信息 <- 用户id
     *
     * @param userId 用户id
     * @return
     */
    public SysUser selectUserById(Long userId);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int addUser(SysUser user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    public int updateUser(SysUser user);

    /**
     * 删除用户 <- 用户id
     * @param userId 用户id
     * @return
     */
    public int deleteUserById(Long userId);

    /**
     * 用户数量 <- 用户名
     * @param loginName 用户名
     * @return
     */
    public int selectCountByLoginName(String loginName);
}
