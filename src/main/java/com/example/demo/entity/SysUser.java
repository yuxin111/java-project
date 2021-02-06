package com.example.demo.entity;

import lombok.Data;

/**
 * 用户信息表
 *
 * @author YuXin
 */
@Data
public class SysUser extends BaseEntity {
    private Long id;
    private String loginName;
    private String password;
}
