package com.example.demo.core.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Autowired
    ISysUserService userService;

    @PostMapping("/list")
    public ResultBody getUserList(@RequestBody SysUser user){
        startPage();
        List<SysUser> userList = userService.selectUserList(user);
        return getDataTable(userList);
    }

    @PostMapping("/insert")
    public ResultBody insertUser(@RequestBody SysUser user){
        int result = userService.insertUser(user);
        if(result > 0){
            return ResultBody.success("保存用户信息成功");
        }else{
            return ResultBody.error("保存用户信息失败");
        }
    }

    @GetMapping("/delete/{userId}")
    public ResultBody insertUser(@PathVariable Long userId){
        int result = userService.deleteUserById(userId);
        if(result > 0){
            return ResultBody.success("删除用户信息成功");
        }else{
            return ResultBody.error("删除用户信息失败");
        }
    }
}
