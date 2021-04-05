package com.example.demo.core.service.impl;

import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.entity.SysRoleMenu;
import com.example.demo.core.mapper.SysMenuMapper;
import com.example.demo.core.mapper.SysRoleMapper;
import com.example.demo.core.mapper.SysRoleMenuMapper;
import com.example.demo.core.mapper.SysUserRoleMapper;
import com.example.demo.core.service.ISysMenuService;
import com.example.demo.core.service.ISysRoleService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    SysMenuMapper menuMapper;

    @Autowired
    SysRoleMenuMapper roleMenuMapper;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;


    @Override
    public List<SysMenu> selectMenuList(SysMenu menu) {
        return menuMapper.selectMenuList(menu);
    }

    @Override
    public List<SysMenu> selectMenusByUserId(Long userId) {
        return menuMapper.selectMenusByUserId(userId);
    }

    @Override
    @Transactional
    public int addMenu(SysMenu menu) {
        valiMenu(menu);
        int rows = menuMapper.addMenu(menu);
        if (rows > 0) {
            batchRoleMenuHasParentId(menu);
        }
        return rows;
    }

    @Override
    @Transactional
    public int updateMenu(SysMenu menu) {
        valiMenu(menu);
        int rows = menuMapper.updateMenu(menu);
        if (rows > 0) {
            batchRoleMenuHasParentId(menu);
        }
        return rows;
    }

    @Override
    @Transactional
    public int deleteMenuById(Long menuId) {
        roleMenuMapper.deleteByMenuId(menuId);
        return menuMapper.deleteMenuById(menuId);
    }

    /**
     * 验证菜单信息
     *
     * @param menu
     */
    private void valiMenu(SysMenu menu) {
        List<SysMenu> menuList;
        SysMenu onceTempMenu = new SysMenu();

        onceTempMenu.setCode(menu.getCode());
        onceTempMenu.setMenuId(menu.getMenuId());
        menuList = menuMapper.selectMenuByParams(onceTempMenu);
        if (StringUtils.hasText(menu.getCode()) && menuList != null && menuList.size() > 0) {
            throw new MyException("菜单标识不能重复");
        }
    }

    /**
     * 新增roleMenu记录
     *
     * @param menu 菜单
     * @desc 当菜单的父菜单有roleMenu绑定记录时，新增与之对应的记录
     * 防止新增菜单后，角色绑定菜单不正确
     */
    private void batchRoleMenuHasParentId(SysMenu menu) {
        Long parentId = menu.getParentId();
        if (parentId != null) {
            List<SysRoleMenu> roleMenuList = roleMenuMapper.selectRoleMenusByMenuId(parentId);
            if (roleMenuList.size() != 0) {
                roleMenuList.forEach(f -> f.setMenuId(menu.getMenuId()));
                roleMenuMapper.batchRoleMenu(roleMenuList);
            }
        }
    }
}
