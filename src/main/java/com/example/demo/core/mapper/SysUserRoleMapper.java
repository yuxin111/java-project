package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysToken;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserRoleMapper {

    /**
     * 删除用户角色数据 <- 用户id
     * @param userId 用户id
     * @return
     */
    public int deleteByUserId(Long userId);

    /**
     * 删除用户角色数据 <- 角色id
     * @param roleId 角色id
     * @return
     */
    public int deleteByRoleId(Long roleId);

    /**
     * 新增用户角色信息
     * @param userRoleList
     * @return
     */
    public int batchUserRole(List<SysUserRole> userRoleList);

}
