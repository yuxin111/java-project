package com.example.demo.core.service.impl;

import com.example.demo.common.utils.SecurityUtils;
import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.entity.SysRoleMenu;
import com.example.demo.core.entity.SysUserRole;
import com.example.demo.core.mapper.SysRoleMapper;
import com.example.demo.core.mapper.SysRoleMenuMapper;
import com.example.demo.core.mapper.SysUserRoleMapper;
import com.example.demo.core.service.ISysRoleService;
import com.example.demo.core.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service

public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    SysUserRoleMapper userRoleMapper;

    @Autowired
    SysRoleMenuMapper roleMenuMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    @Transactional
    public int addRole(SysRole role) {
        valiRole(role);
        int rows = roleMapper.addRole(role);

        // 新增角色菜单关联
        batchRoleMenu(role.getRoleId(),role.getMenuIds());

        return rows;
    }

    @Override
    @Transactional
    public int updateRole(SysRole role) {
        valiRole(role);

        if(role.getMenuIds() != null){
            // 删除原有角色菜单关联
            roleMenuMapper.deleteByRoleId(role.getRoleId());
            // 新增角色菜单关联
            batchRoleMenu(role.getRoleId(),role.getMenuIds());
        }

        return roleMapper.updateRole(role);
    }



    @Override
    @Transactional
    public int deleteRoleById(Long roleId) {
        // 删除用户角色关联
        userRoleMapper.deleteByRoleId(roleId);
        return roleMapper.deleteRoleById(roleId);
    }



    /**
     * 验证角色信息
     * @param role
     */
    private void valiRole(SysRole role) {
        List<SysRole> roleList;
        SysRole onceTempRole = new SysRole();
        SysRole secondTempRole = new SysRole();

        if(StringUtils.hasText(role.getRoleName())){
            onceTempRole.setRoleName(role.getRoleName());
            onceTempRole.setRoleId(role.getRoleId());
            roleList = roleMapper.selectRolesByParams(onceTempRole);
            if(roleList != null && roleList.size() > 0){
                throw new MyException("角色名称不能重复");
            }
        }

        if(StringUtils.hasText(role.getCode())){
            secondTempRole.setCode(role.getCode());
            secondTempRole.setRoleId(role.getRoleId());
            roleList = roleMapper.selectRolesByParams(secondTempRole);
            if(roleList != null && roleList.size() > 0){
                throw new MyException("角色代码不能重复");
            }
        }
    }

    /**
     * 新增角色菜单
     * @param roleId
     * @param menuIds
     */
    private void batchRoleMenu(Long roleId, Long[] menuIds) {
        if(menuIds != null && menuIds.length > 0){
            List<SysRoleMenu> roleMenuList = new ArrayList<>();
            SysRoleMenu roleMenu;
            for (Long menuId : menuIds){
                roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuList.add(roleMenu);
            }
            roleMenuMapper.batchRoleMenu(roleMenuList);
        }
    }
}
