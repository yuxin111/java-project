package com.example.demo.core.entity.request;

import lombok.Data;

/**
 * 单一输入模块：输出字段
 */
@Data
public class SingleInputOutput {
    // 计算表达式
    private String calcExpress;
    // 计算结果名
    private String name;
}
