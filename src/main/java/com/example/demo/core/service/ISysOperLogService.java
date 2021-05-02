package com.example.demo.core.service;

import com.example.demo.core.entity.SysOperLog;
import com.example.demo.core.entity.SysRole;

import java.util.List;

public interface ISysOperLogService {

    /**
     * 新增操作日志
     * @param operLog 操作日志
     * @return
     */
    public int addOperLog(SysOperLog operLog);


}
