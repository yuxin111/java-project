package com.example.demo.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncTask {

    @Async("selfServiceExecutor")
    public void dealNoReturnTask(){
        log.info("Thread {} deal No Return Task start", Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Thread {} deal No Return Task end at {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }
}