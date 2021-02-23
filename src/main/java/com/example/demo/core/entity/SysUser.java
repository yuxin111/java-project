package com.example.demo.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户信息表
 *
 * @author YuXin
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SysUser extends BaseEntity {
    private Long userId;
    private String loginName;
    private String password;

    private List<SysRole> roles;
}
