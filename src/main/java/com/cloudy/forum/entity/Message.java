package com.cloudy.forum.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (message)实体类
 *
 * @author yun fan
 * @since 2022-09-28 00:55:46
 * @description 自动类
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * fromId
     */
    private Integer fromId;
    /**
     * toId
     */
    private Integer toId;
    /**
     * conversationId
     */
    private String conversationId;
    /**
     * content
     */
    private String content;
    /**
     * 0-未读;1-已读;2-删除;
     */
    private Integer status;
    /**
     * createTime
     */
    private Date createTime;

}