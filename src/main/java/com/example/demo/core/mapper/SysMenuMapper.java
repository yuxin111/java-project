package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysMenuMapper {

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
     * @param menu
     * @return
     */
    public int addMenu(SysMenu menu);

    /**
     * 更新菜单 <- 菜单信息
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

    /**
     * 菜单列表 <- 菜单参数
     *
     * @param menu 菜单参数
     * @desc 若参数对象存在主键，则会查询出不包括该主键的对象列表
     * @return 菜单信息
     */
    List<SysMenu> selectMenuByParams(SysMenu menu);


}
