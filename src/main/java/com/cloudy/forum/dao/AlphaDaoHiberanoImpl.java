package com.cloudy.forum.dao;

import org.springframework.stereotype.Repository;

/**
 * @auther li bin
 * @create 2022-09-27 22:42
 */
@Repository("alphahiberano")
public class AlphaDaoHiberanoImpl implements AlphaDao {

    @Override
    public String select() {
        return "hiberano";
    }
}
