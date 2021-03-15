package com.example.demo.core.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.entity.SysMenu;
import com.example.demo.core.entity.SysRole;
import com.example.demo.core.service.ISysMenuService;
import com.example.demo.core.service.ISysRoleService;
import com.example.demo.enums.CommonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {

    @Autowired
    ISysMenuService menuService;

    @PostMapping("/list")
    public ResultBody getMenuList(@RequestBody SysMenu menu){
        List<SysMenu> menuList = menuService.selectMenuList(menu);
        return ResultBody.success(menuList);
    }
}
