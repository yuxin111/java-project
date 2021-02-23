package com.example.demo.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
