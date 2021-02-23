package com.example.demo.core.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Autowired
    ISysUserService userService;

    @PostMapping("/list")
    public ResultBody getUserList(SysUser user){
        startPage();
        List<SysUser> userList = userService.selectUserList(user);
        return getDataTable(userList);
    }
}
