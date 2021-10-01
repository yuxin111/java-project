package com.example.demo.core.controller;

import com.example.demo.common.annotation.AllowDevPass;
import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysOperLog;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.service.ISysOperLogService;
import com.example.demo.core.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operLog")
public class SysOperLogController extends BaseController {

    @Autowired
    ISysOperLogService operLogService;

    @AllowDevPass
    @RequiresPermissions("log:operLog:list")
    @PostMapping("/list")
    public ResultBody getOperLogList(@RequestBody SysOperLog operLog){
        startPage();
        List<SysOperLog> operLogList = operLogService.selectOperLogList(operLog);
        return getDataTable(operLogList);
    }

    @GetMapping("/get/{operLogId}")
    public ResultBody getUserList(@PathVariable("operLogId") Long operLogId){
        SysOperLog operLog = operLogService.selectOperLogById(operLogId);
        return ResultBody.success(operLog);
    }
}
