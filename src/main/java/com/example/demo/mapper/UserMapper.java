package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 查询所有
     * @return
     */
    public List<User> queryAll();

    /**
     * 添加数据
     * @param user
     * @return
     */
    public int add(User user);
}
