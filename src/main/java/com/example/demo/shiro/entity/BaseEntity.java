package com.example.demo.shiro.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
