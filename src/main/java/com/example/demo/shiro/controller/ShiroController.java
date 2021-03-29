package com.example.demo.shiro.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.common.utils.SecurityUtils;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.core.dto.UserDto;
import com.example.demo.core.entity.SysUser;
import com.example.demo.core.service.ISysTokenService;
import com.example.demo.core.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShiroController extends BaseController {

    @Autowired
    ISysUserService userService;

    @Autowired
    ISysTokenService tokenService;

    @PostMapping("/shiro/login")
    public ResultBody login(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        SysUser sysUser = userService.selectUserByLoginName(username);
        ResultBody resultBody = new ResultBody();
        if (sysUser == null) {
            resultBody.setCode(404);
            resultBody.setMessage("登录账号不存在，请检查");
        } else if (!sysUser.getPassword().equals(SecurityUtils.md5(password))) {
            resultBody.setCode(400);
            resultBody.setMessage("密码错误，请重新输入");
        } else {
            Map<String, Object> result = tokenService.createToken(sysUser.getUserId());
            result.put("menus", sysUser.getMenuCodes());
            resultBody.setCode(200);
            resultBody.setMessage("登录成功");
            resultBody.setResult(result);
        }
        return resultBody;
    }

    @PostMapping("/shiro/test")
    public ResultBody test() {
        return ResultBody.success();
    }
}
