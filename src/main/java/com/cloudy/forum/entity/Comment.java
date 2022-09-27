package com.cloudy.forum.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (comment)实体类
 *
 * @author yun fan
 * @since 2022-09-28 00:55:46
 * @description 自动类
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * userId
     */
    private Integer userId;
    /**
     * entityType
     */
    private Integer entityType;
    /**
     * entityId
     */
    private Integer entityId;
    /**
     * targetId
     */
    private Integer targetId;
    /**
     * content
     */
    private String content;
    /**
     * status
     */
    private Integer status;
    /**
     * createTime
     */
    private Date createTime;

}