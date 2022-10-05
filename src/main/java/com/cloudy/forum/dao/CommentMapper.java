package com.cloudy.forum.dao;

import com.cloudy.forum.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (comment)数据Mapper
 *
 * @author yun fan
 * @since 2022-09-28 00:53:37
 * @description 自动类
*/
@Mapper
public interface CommentMapper{
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);
    int selectCountByEntity(int entityType, int entityId);
}
