package com.cloudy.forum.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @auther li bin
 * @create 2022-09-27 22:49
 */
@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "mybatis dao";
    }
}
