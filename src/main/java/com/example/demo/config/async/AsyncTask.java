package com.example.demo.config.async;

import com.example.demo.core.entity.SysOperLog;
import com.example.demo.core.service.ISysOperLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncTask {

    @Autowired
    ISysOperLogService operLogService;

    @Async("selfServiceExecutor")
    public void recordOperLog(SysOperLog operLog){
        operLogService.addOperLog(operLog);
    }

}