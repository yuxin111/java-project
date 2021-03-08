package com.example.demo.core.service;

import com.example.demo.core.entity.SysRole;

import java.util.List;

public interface ISysRoleService {

    /**
     * 角色列表 <- 分页
     *
     * @param role 角色信息
     * @return
     */
    public List<SysRole> selectRoleList(SysRole role);

    /**
     * 角色信息 <- 角色名
     * @param loginName 登录名
     * @return
     */
    public SysRole selectRoleByLoginName(String loginName);

    /**
     * 角色信息 <- 角色id
     * @param roleId 角色id
     * @return
     */
    public SysRole selectRoleById(Long roleId);


    /**
     * 新增角色
     * @param role 角色
     * @return
     */
    public int addRole(SysRole role);
//
//    /**
//     * 更新角色
//     * @param role
//     * @return
//     */
//    public int updateRole(SysRole role);
//
//    /**
//     * 删除角色 <- 角色id
//     * @param roleId 角色id
//     * @return
//     */
//    public int deleteRoleById(Long roleId);

}
