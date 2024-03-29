package com.example.demo.core.service.impl;

import com.example.demo.common.utils.SecurityUtils;
import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.entity.SysUserRole;
import com.example.demo.core.mapper.SysMenuMapper;
import com.example.demo.core.mapper.SysUserMapper;
import com.example.demo.core.mapper.SysUserRoleMapper;
import com.example.demo.core.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysUserRoleMapper userRoleMapper;

    @Autowired
    SysMenuMapper menuMapper;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public SysUser selectUserByLoginName(String loginName) {
        SysUser user = userMapper.selectUserByLoginName(loginName);
        Set<String> menuCodes = new HashSet<>();
        if(user != null){
            List<SysMenu> menus = menuMapper.selectMenusByUserId(user.getUserId());
            for(SysMenu menu : menus){
                String menuCode = menu.getCode();
                if(StringUtils.hasText(menuCode)){
                    menuCodes.add(menuCode);
                }
            }
            user.setMenuCodes(menuCodes);
        }
        return user;
    }

    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    @Transactional
    public int addUser(SysUser user) {
        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(SecurityUtils.md5(user.getPassword()));
        }else{
            throw new MyException("请输入密码");
        }

        SysUser tempUser = userMapper.selectUserByLoginName(user.getLoginName());
        if(tempUser != null){
            throw new MyException("登录账号不能重复");
        }

        int rows = userMapper.addUser(user);

        // 新增用户角色关联
        batchUserRole(user.getUserId(),user.getRoleIds());

        return rows;
    }

    @Override
    @Transactional
    public int updateUser(SysUser user) {
        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(SecurityUtils.md5(user.getPassword()));
        }

        List<SysUser> userList;
        SysUser onceUser = new SysUser();
        onceUser.setUserId(user.getUserId());
        onceUser.setLoginName(user.getLoginName());
        userList = userMapper.selectUserByParams(onceUser);
        if(userList != null && userList.size() > 0){
            throw new MyException("登录账号不能重复");
        }

        if(user.getRoleIds() != null){
            // 删除原有用户角色关联
            userRoleMapper.deleteByUserId(user.getUserId());
            // 新增用户角色关联
            batchUserRole(user.getUserId(),user.getRoleIds());
        }

        return userMapper.updateUser(user);
    }

    @Override
    @Transactional
    public int deleteUserById(Long userId) {
        // 删除用户关联角色
        userRoleMapper.deleteByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 新增用户角色
     * @param userId 用户id
     * @param roleIds 角色id列表
     */
    private void batchUserRole(Long userId, Long[] roleIds) {
        if(roleIds != null && roleIds.length > 0){
            List<SysUserRole> userRoleList = new ArrayList<>();
            SysUserRole userRole;
            for (Long roleId : roleIds){
                userRole = new SysUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(userId);
                userRoleList.add(userRole);
            }
            userRoleMapper.batchUserRole(userRoleList);
        }
    }
}
