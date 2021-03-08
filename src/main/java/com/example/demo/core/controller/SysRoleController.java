package com.example.demo.core.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController {

    @Autowired
    ISysRoleService roleService;

    @PostMapping("/list")
    public ResultBody getRoleList(@RequestBody SysRole role){
        startPage();
        List<SysRole> roleList = roleService.selectRoleList(role);
        return getDataTable(roleList);
    }

    @PostMapping("/add")
    public ResultBody addRole(@RequestBody SysRole role){
        int result = roleService.addRole(role);
        if(result > 0){
            return ResultBody.success("新增角色信息成功");
        }else{
            return ResultBody.error("新增角色信息失败");
        }
    }
//
//    @PostMapping("/update")
//    public ResultBody updateRole(@RequestBody SysRole role){
//        int result = roleService.updateRole(role);
//        if(result > 0){
//            return ResultBody.success("更新角色信息成功");
//        }else{
//            return ResultBody.error("更新角色信息失败");
//        }
//    }
//
//    @GetMapping("/delete/{roleId}")
//    public ResultBody deleteRole(@PathVariable Long roleId){
//        int result = roleService.deleteRoleById(roleId);
//        if(result > 0){
//            return ResultBody.success("删除角色信息成功");
//        }else{
//            return ResultBody.error("删除角色信息失败");
//        }
//    }
}
