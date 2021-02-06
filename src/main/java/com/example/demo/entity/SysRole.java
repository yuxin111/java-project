package com.example.demo.entity;

import lombok.Data;

/**
 * 用户角色表
 *
 * @author YuXin
 */
@Data
public class SysRole extends BaseEntity {
    private Long id;
    private String roleName;
}
