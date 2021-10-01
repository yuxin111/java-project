package com.example.demo.core.controller;

import com.example.demo.common.annotation.AllowDevPass;
import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.service.ISysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController {

    @Autowired
    ISysRoleService roleService;

    @AllowDevPass
    @PostMapping("/list")
    public ResultBody getRoleList(@RequestBody SysRole role){
        startPage();
        List<SysRole> roleList = roleService.selectRoleList(role);
        return getDataTable(roleList);
    }

    @AllowDevPass
    @GetMapping("/get/{roleId}")
    public ResultBody getRoleList(@PathVariable("roleId") Long roleId){
        SysRole role = roleService.selectRoleById(roleId);
        return ResultBody.success(role);
    }

    @MyLog("新增角色")
    @RequiresPermissions("system:role:add")
    @PostMapping("/add")
    public ResultBody addRole(@RequestBody SysRole role){
        int result = roleService.addRole(role);
        if(result > 0){
            return ResultBody.success("新增角色信息成功");
        }else{
            return ResultBody.error("新增角色信息失败");
        }
    }

    @MyLog("更新角色")
    @RequiresPermissions("system:role:edit")
    @PostMapping("/update")
    public ResultBody updateRole(@RequestBody SysRole role){
        int result = roleService.updateRole(role);
        if(result > 0){
            return ResultBody.success("更新角色信息成功");
        }else{
            return ResultBody.error("更新角色信息失败");
        }
    }

    @MyLog("删除角色")
    @RequiresPermissions("system:role:delete")
    @GetMapping("/delete/{roleId}")
    public ResultBody deleteRole(@PathVariable Long roleId){
        int result = roleService.deleteRoleById(roleId);
        if(result > 0){
            return ResultBody.success("删除角色信息成功");
        }else{
            return ResultBody.error("删除角色信息失败");
        }
    }
}
