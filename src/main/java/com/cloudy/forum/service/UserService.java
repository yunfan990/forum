package com.cloudy.forum.service;


import com.cloudy.forum.dao.UserMapper;
import com.cloudy.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务接口
 *
 * @author yun fan
 * @since 2022-09-28 00:55:01
 * @description 自动类
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectUserById(id);
    }
}
