package com.example.demo.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Long[] roleIds;
    private List<SysRole> roles;
    private List<String> menuCodes;

//    @JsonIgnore
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
