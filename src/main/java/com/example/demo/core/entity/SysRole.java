package com.example.demo.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户角色表
 *
 * @author YuXin
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SysRole extends BaseEntity {
    private Long roleId;
    private String roleName;
    // 角色状态（0停用，1正常）
    private String status;

    private List<SysMenu> menus;
}
