package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleMapper {
    /**
     * 角色列表 <- 角色信息
     *
     * @param sysRole 角色信息
     * @return 角色信息列表
     */
    public List<SysRole> selectRoleList(SysRole sysRole);

    /**
     * 角色信息 <- 角色名
     *
     * @param loginName 角色名
     * @return 角色信息
     */
    public SysRole selectRoleByLoginName(String loginName);

    /**
     * 角色信息 <- 角色id
     *
     * @param roleId 角色id
     * @return
     */
    public SysRole selectRoleById(Long roleId);

    /**
     * 新增角色
     * @param role
     * @return
     */
    public int addRole(SysRole role);

    /**
     * 更新角色
     * @param role
     * @return
     */
    public int updateRole(SysRole role);

    /**
     * 删除角色 <- 角色id
     * @param roleId 角色id
     * @return
     */
    public int deleteRoleById(Long roleId);
}
