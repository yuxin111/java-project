package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/10/5
 **/
@Data
@Component
@ConfigurationProperties("img")
public class FileConfig {
    private List extensions;    // 支持扩展名
    private String location;    // 图片存储位置
}
