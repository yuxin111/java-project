package com.example.demo.core.mapper;

import com.example.demo.core.entity.SysOperLog;
import com.example.demo.core.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysOperLogMapper {

    /**
     * 新增操作日志
     * @param operLog 操作日志
     * @return
     */
    public int addOperLog(SysOperLog operLog);

}
