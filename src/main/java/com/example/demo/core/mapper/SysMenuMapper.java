package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;
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
}
