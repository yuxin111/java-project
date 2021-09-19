package com.example.demo.core.controller;

import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/9/17
 **/
@RestController
@RequestMapping("/test")
public class SysTestController extends BaseController {

    @GetMapping("/startProcess")
    public void startProcess() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("java -jar D:\\Project\\webShell\\backend\\target\\cn-0.0.1-SNAPSHOT.jar");
        process.waitFor();
        System.out.println("正在执行脚本");
    }
}
