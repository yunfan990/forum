package com.cloudy.forum.dao;

import com.cloudy.forum.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (discuss_post)数据Mapper
 *
 * @author yun fan
 * @since 2022-09-28 00:53:37
 * @description 自动类
*/
@Mapper
public interface DiscussPostMapper{
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //@param注解参数别名（动态参数，有且只有一个参数）
    int selectDiscussPostRows(@Param("userId") int userId);


}
