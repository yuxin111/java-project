package com.example.demo.core.service.impl;

import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.mapper.SysMenuMapper;
import com.example.demo.core.mapper.SysRoleMapper;
import com.example.demo.core.mapper.SysUserRoleMapper;
import com.example.demo.core.service.ISysMenuService;
import com.example.demo.core.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu) {
        return menuMapper.selectMenuList(menu);
    }

    @Override
    public int addMenu(SysMenu menu) {
        valiMenu(menu);
        return menuMapper.addMenu(menu);
    }

    @Override
    public int updateMenu(SysMenu menu) {
        valiMenu(menu);
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenuById(Long menuId) {
        // 删除用户角色关联
//        userRoleMapper.deleteByRoleId(roleId);
        return menuMapper.deleteMenuById(menuId);
    }

    /**
     * 验证菜单信息
     * @param menu
     */
    private void valiMenu(SysMenu menu) {
//        List<SysRole> roleList;
//        SysRole onceTempRole = new SysRole();
//        SysRole secondTempRole = new SysRole();
//
//        onceTempRole.setRoleName(role.getRoleName());
//        onceTempRole.setRoleId(role.getRoleId());
//        roleList = roleMapper.selectRoleByParams(onceTempRole);
//        if(roleList != null && roleList.size() > 0){
//            throw new MyException("角色名称不能重复");
//        }
//
//        secondTempRole.setCode(role.getCode());
//        secondTempRole.setRoleId(role.getRoleId());
//        roleList = roleMapper.selectRoleByParams(secondTempRole);
//        if(roleList != null && roleList.size() > 0){
//            throw new MyException("角色代码不能重复");
//        }
    }
}
