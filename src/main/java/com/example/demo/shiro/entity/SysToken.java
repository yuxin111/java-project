package com.example.demo.shiro.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * token表
 *
 * @author YuXin
 */
@Data
public class SysToken implements Serializable {

    private Long userId;
    private String token;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;

}
