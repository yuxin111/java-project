package com.example.demo.core.entity;

import lombok.Data;

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
