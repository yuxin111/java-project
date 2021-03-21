package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysMenuMapper {

    /**
     * 菜单列表
     *
     * @param menu 菜单信息
     * @return
     */
    public List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    public int addMenu(SysMenu menu);

    /**
     * 更新菜单
     * @param menu
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
