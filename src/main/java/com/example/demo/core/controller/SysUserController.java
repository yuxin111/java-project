package com.example.demo.core.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Autowired
    ISysUserService userService;

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    public ResultBody getUserList(@RequestBody SysUser user){
        startPage();
        List<SysUser> userList = userService.selectUserList(user);
        return getDataTable(userList);
    }

    @GetMapping("/get/{userId}")
    public ResultBody getUserList(@PathVariable("userId") Long userId){
        SysUser user = userService.selectUserById(userId);
        return ResultBody.success(user);
    }

    @RequiresPermissions("system:user:add")
    @PostMapping("/add")
    public ResultBody addUser(@RequestBody SysUser user){
        int result = userService.addUser(user);
        if(result > 0){
            return ResultBody.success("新增用户信息成功");
        }else{
            return ResultBody.error("新增用户信息失败");
        }
    }

    @RequiresPermissions("system:user:edit")
    @PostMapping("/update")
    public ResultBody updateUser(@RequestBody SysUser user){
        int result = userService.updateUser(user);
        if(result > 0){
            return ResultBody.success("更新用户信息成功");
        }else{
            return ResultBody.error("更新用户信息失败");
        }
    }

    @RequiresPermissions("system:user:delete")
    @GetMapping("/delete/{userId}")
    public ResultBody deleteUser(@PathVariable Long userId){
        int result = userService.deleteUserById(userId);
        if(result > 0){
            return ResultBody.success("删除用户信息成功");
        }else{
            return ResultBody.error("删除用户信息失败");
        }
    }

}
