package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysRoleMenu;
import com.example.demo.core.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleMenuMapper {

    /**
     * 角色菜单列表 <- 菜单id
     * @param menuId 菜单id
     * @return
     */
    public List<SysRoleMenu> selectRoleMenusByMenuId(Long menuId);

    /**
     * 角色菜单列表 <- 角色id
     * @param roleId 角色id
     * @return
     */
    public List<SysRoleMenu> selectRoleMenusByRoleId(Long roleId);

    /**
     * 删除角色菜单数据 <- 角色id
     * @param roleId 角色id
     * @return
     */
    public int deleteByRoleId(Long roleId);

    /**
     * 删除角色菜单数据 <- 菜单id
     * @param menuId 菜单id
     * @return
     */
    public int deleteByMenuId(Long menuId);

    /**
     * 新增用户角色信息
     * @param roleMenuList
     * @return
     */
    public int batchRoleMenu(List<SysRoleMenu> roleMenuList);

}
