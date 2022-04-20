package com.example.demo.core.mapper;

import com.example.demo.core.entity.request.SingleInput;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SingleInputTableMapper {
    /**
     * 列表 <- 信息
     */
    @MapKey("mobile")
    public List<Map<String,Object>> selectSingleInutTableList(SingleInput singleInput);

}
