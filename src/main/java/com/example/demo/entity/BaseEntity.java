package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
