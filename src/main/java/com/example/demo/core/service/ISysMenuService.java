package com.example.demo.core.service;

import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;

import java.util.List;

public interface ISysMenuService {

    /**
     * 菜单列表
     *
     * @param menu 菜单信息
     * @return
     */
    public List<SysMenu> selectMenuList(SysMenu menu);
}
