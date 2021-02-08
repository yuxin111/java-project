package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
