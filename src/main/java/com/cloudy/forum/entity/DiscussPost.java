package com.cloudy.forum.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * (discuss_post)实体类
 *
 * @author yun fan
 * @since 2022-09-28 00:55:46
 * @description 自动类
 */
public class DiscussPost implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * userId
     */
    private String userId;
    /**
     * title
     */
    private String title;
    /**
     * content
     */
    private String content;
    /**
     * 0-普通; 1-置顶;
     */
    private Integer type;
    /**
     * 0-正常; 1-精华; 2-拉黑;
     */
    private Integer status;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * commentCount
     */
    private Integer commentCount;
    /**
     * score
     */
    private Double score;

}