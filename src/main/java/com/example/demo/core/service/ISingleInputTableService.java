package com.example.demo.core.service;

import com.example.demo.core.entity.SingleInputTable;
import com.example.demo.core.entity.request.SingleInput;

import java.util.List;

public interface ISingleInputTableService {

    /**
     * 列表 <- 分页
     *
     * @param singleInput 复合查询信息
     * @return
     */
    public List<SingleInputTable> selectSingleInutTableList(SingleInput singleInput);

}
