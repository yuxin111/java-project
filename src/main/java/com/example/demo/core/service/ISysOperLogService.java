package com.example.demo.core.service;

import com.example.demo.core.entity.SysOperLog;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.entity.SysUser;

import java.util.List;

public interface ISysOperLogService {

    /**
     * 新增操作日志
     * @param operLog 操作日志
     * @return
     */
    public int addOperLog(SysOperLog operLog);

    /**
     * 操作日志列表 <- 分页
     *
     * @param operLog 操作日志
     * @return
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 操作日志列表 <- 操作日志id
     * @param operLogId 操作日志id
     * @return
     */
    public SysOperLog selectOperLogById(Long operLogId);
}
