package com.cloudy.forum.dao;

import com.cloudy.forum.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (user)数据Mapper
 *
 * @author yun fan
 * @since 2022-09-28 00:52:00
 * @description 自动类
*/
@Mapper
public interface UserMapper {
    public User selectUserById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}
