package com.example.demo.core.service.impl;

import com.example.demo.common.utils.SecurityUtils;
import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.mapper.SysRoleMapper;
import com.example.demo.core.service.ISysRoleService;
import com.example.demo.core.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    public int addRole(SysRole role) {
        valiRole(role);
        return roleMapper.addRole(role);
    }

    @Override
    public int updateRole(SysRole role) {
        valiRole(role);
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteRoleById(Long roleId) {
        // 删除角色关联角色
//        roleRoleMapper.deleteByRoleId(roleId);
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

        onceTempRole.setRoleName(role.getRoleName());
        onceTempRole.setRoleId(role.getRoleId());
        roleList = roleMapper.selectRoleByParams(onceTempRole);
        if(roleList != null && roleList.size() > 0){
            throw new MyException("角色名称不能重复");
        }

        secondTempRole.setCode(role.getCode());
        secondTempRole.setRoleId(role.getRoleId());
        roleList = roleMapper.selectRoleByParams(secondTempRole);
        if(roleList != null && roleList.size() > 0){
            throw new MyException("角色代码不能重复");
        }
    }
}
