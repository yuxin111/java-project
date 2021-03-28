package com.example.demo.core.service;

import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;

import java.util.List;

public interface ISysMenuService {

    /**
     * 菜单列表 <- 菜单信息
     *
     * @param menu 菜单信息
     * @return
     */
    public List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 菜单列表 <- 用户id
     * @param userId 用户id
     * @return
     */
    List<SysMenu> selectMenusByUserId(Long userId);

    /**
     * 新增菜单 <- 菜单信息
     * @param menu 菜单
     * @return
     */
    public int addMenu(SysMenu menu);

    /**
     * 更新菜单 <- 菜单信息
     * @param menu 菜单
     * @return
     */
    public int updateMenu(SysMenu menu);

    /**
     * 删除菜单 <- 菜单id
     * @param menuId 菜单id
     * @return
     */
    public int deleteMenuById(Long menuId);


}
