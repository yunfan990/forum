package com.cloudy.forum.service;


import com.cloudy.forum.dao.DiscussPostMapper;
import com.cloudy.forum.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务接口
 *
 * @author yun fan
 * @since 2022-09-28 00:55:46
 * @description 自动类
 */
@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit){
        return  discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDiscussPostRows(int userId){
        return  discussPostMapper.selectDiscussPostRows(userId);
    }
}
