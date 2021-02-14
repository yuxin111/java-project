package com.example.demo.shiro.controller;

import com.example.demo.shiro.entity.SysUser;
import com.example.demo.shiro.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShiroController {

    @Autowired
    IShiroService shiroService;

    @GetMapping("/shiro/login")
    public Map<String, Object> login(String username, String password) {

        Map<String, Object> result = new HashMap<>();
        SysUser sysUser = shiroService.selectUserByLoginName(username);

        if (sysUser == null) {
            result.put("status", "404");
            result.put("msg", "账号不存在");
        } else if (!sysUser.getPassword().equals(password)) {
            result.put("status", "400");
            result.put("msg", "密码错误");
        } else {
            result = shiroService.createToken(sysUser.getUserId());
            result.put("status", "200");
            result.put("msg", "登录成功");
        }

        return result;
    }

    @GetMapping("/shiro/test")
    public Map<String, Object> test() {

        Map<String, Object> result = new HashMap<>();
        result.put("status", "200");
        result.put("msg", "hello");

        return result;

    }
}
