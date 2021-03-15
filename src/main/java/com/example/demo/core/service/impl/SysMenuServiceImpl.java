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
}
