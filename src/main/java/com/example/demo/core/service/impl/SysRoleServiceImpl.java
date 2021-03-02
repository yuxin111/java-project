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

import java.util.List;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public SysRole selectRoleByLoginName(String loginName) {
        return roleMapper.selectRoleByLoginName(loginName);
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

//    @Override
//    public int addRole(SysRole role) {
//        if(StringUtils.hasText(role.getPassword())){
//            role.setPassword(SecurityUtils.md5(role.getPassword()));
//        }else{
//            throw new MyException("请输入密码");
//        }
//        SysRole repeatRole = roleMapper.selectRoleByLoginName(role.getLoginName());
//        if(repeatRole != null){
//            throw new MyException("角色名不能重复");
//        }
//        return roleMapper.addRole(role);
//    }
//
//    @Override
//    public int updateRole(SysRole role) {
//        if(StringUtils.hasText(role.getPassword())){
//            role.setPassword(SecurityUtils.md5(role.getPassword()));
//        }
//        SysRole onceRole = roleMapper.selectRoleById(role.getRoleId());
//        if(onceRole == null){
//            throw new MyException("更新的角色不存在");
//        }
//        onceRole = roleMapper.selectRoleByLoginName(role.getLoginName());
//        if(onceRole != null){
//            throw new MyException("已存在重复的登录账号，请重新命名");
//        }
//        return roleMapper.updateRole(role);
//    }
//
//    @Override
//    @Transactional
//    public int deleteRoleById(Long roleId) {
//        // 删除角色关联角色
//        roleRoleMapper.deleteByRoleId(roleId);
//        return roleMapper.deleteRoleById(roleId);
//    }
}
