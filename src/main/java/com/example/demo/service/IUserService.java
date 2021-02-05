package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface IUserService {

    public List<User> queryAll();

    String add(int id, String name);
}
