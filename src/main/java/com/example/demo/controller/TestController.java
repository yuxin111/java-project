package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    IUserService userService;

    @GetMapping("/hello")
    public String hello() {
        String user = "123";
        return user;
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.queryAll();
    }

    @GetMapping("/add")
    public String add(int id,String name){
        return userService.add(id,name);
    }


}
