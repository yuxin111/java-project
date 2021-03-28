package com.example.demo.core.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.service.ISysMenuService;
import com.example.demo.core.service.ISysMenuService;
import com.example.demo.enums.CommonEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {

    @Autowired
    ISysMenuService menuService;

    @RequiresPermissions("system:menu:list")
    @PostMapping("/list")
    public ResultBody getMenuList(@RequestBody SysMenu menu){
        List<SysMenu> menuList = menuService.selectMenuList(menu);
        return ResultBody.success(menuList);
    }

    @PostMapping("/add")
    public ResultBody addMenu(@RequestBody SysMenu menu){
        int result = menuService.addMenu(menu);
        if(result > 0){
            return ResultBody.success("新增菜单成功");
        }else{
            return ResultBody.error("新增菜单失败");
        }
    }

    @PostMapping("/update")
    public ResultBody updateMenu(@RequestBody SysMenu menu){
        int result = menuService.updateMenu(menu);
        if(result > 0){
            return ResultBody.success("更新菜单成功");
        }else{
            return ResultBody.error("更新菜单失败");
        }
    }

    @GetMapping("/delete/{menuId}")
    public ResultBody deleteMenu(@PathVariable Long menuId){
        int result = menuService.deleteMenuById(menuId);
        if(result > 0){
            return ResultBody.success("删除菜单成功");
        }else{
            return ResultBody.error("删除菜单失败");
        }
    }
}
