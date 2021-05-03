package com.example.demo.core.service.impl;

import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.SysOperLog;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.entity.SysRoleMenu;
import com.example.demo.core.mapper.SysOperLogMapper;
import com.example.demo.core.mapper.SysRoleMapper;
import com.example.demo.core.mapper.SysRoleMenuMapper;
import com.example.demo.core.mapper.SysUserRoleMapper;
import com.example.demo.core.service.ISysOperLogService;
import com.example.demo.core.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysOperLogServiceImpl implements ISysOperLogService {

    @Autowired
    SysOperLogMapper operLogMapper;

    @Override
    public int addOperLog(SysOperLog operLog) {
        return operLogMapper.addOperLog(operLog);
    }

    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return operLogMapper.selectOperLogList(operLog);
    }

}
