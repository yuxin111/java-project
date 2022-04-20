package com.example.demo.core.entity.request;

import lombok.Data;

import java.util.List;

/**
 * 单一输入
 */
@Data
public class SingleInput {
    // 单一输入模块：普通查询
    private SingleInputSearch singleInputSearch;
    // 单一输入模块：输出字段
    private List<SingleInputOutput> singleInputOutputList;
}
