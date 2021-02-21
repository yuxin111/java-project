package com.example.demo.shiro.controller;

import com.example.demo.core.entity.ResultBody;
import com.example.demo.shiro.entity.SysUser;
import com.example.demo.shiro.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {

    @Autowired
    IShiroService shiroService;

    @PostMapping("/shiro/login")
    public ResultBody login(String username, String password) {
        SysUser sysUser = shiroService.selectUserByLoginName(username);
        ResultBody resultBody = new ResultBody();
        if (sysUser == null) {
            resultBody.setCode(404);
            resultBody.setMessage("登录账号不存在，请检查");
        } else if (!sysUser.getPassword().equals(password)) {
            resultBody.setCode(400);
            resultBody.setMessage("密码错误，请重新输入");
        } else {
            resultBody.setCode(200);
            resultBody.setMessage("登录成功");
            resultBody.setResult(shiroService.createToken(sysUser.getUserId()));
        }
        return resultBody;
    }
}
