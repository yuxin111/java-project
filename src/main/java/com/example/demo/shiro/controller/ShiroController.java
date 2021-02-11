package com.example.demo.shiro.controller;

import com.example.demo.shiro.entity.SysUser;
import com.example.demo.shiro.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShiroController {

    @Autowired
    IShiroService shiroService;

    public Map<String,Object> login(String username, String password){

        Map<String,Object> result = new HashMap<>();
        SysUser sysUser = shiroService.selectUserByLoginName(username);

        if(sysUser == null){
            result.put("status","404");
            result.put("msg","账号不存在");
        }

        if(!sysUser.getPassword().equals(password)){
            result.put("status","400");
            result.put("msg","密码错误");
        }


        return result;
    }
}
