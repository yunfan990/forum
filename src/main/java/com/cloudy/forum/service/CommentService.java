package com.cloudy.forum.service;


import com.cloudy.forum.dao.CommentMapper;
import com.cloudy.forum.entity.Comment;
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
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit){
        return  commentMapper.selectCommentsByEntity(entityType, entityId, offset, limit);
    }
    public int findCommentCount(int entityType, int entityId){
        return  commentMapper.selectCountByEntity(entityType, entityId);
    }


}
