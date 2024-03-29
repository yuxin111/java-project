package com.example.demo.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表
 *
 * @author YuXin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity {
    private Long menuId;
    private String menuName;
    private Long parentId;
    private String code;

    // 子菜单
    private List<SysMenu> children = new ArrayList<SysMenu>();

}
