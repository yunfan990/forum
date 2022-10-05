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

	private int id;
    /**
     * userId
     */
    private int userId;
    /**
     * entityType
     */
    private int entityType;
    /**
     * entityId
     */
    private int entityId;
    /**
     * targetId
     */
    private int targetId;
    /**
     * content
     */
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * status
     */
    private int status;
    /**
     * createTime
     */
    private Date createTime;

}