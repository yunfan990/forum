package com.cloudy.forum.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (user)实体类
 *
 * @author yun fan
 * @since 2022-09-28 00:55:01
 * @description 自动类
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * salt
     */
    private String salt;
    /**
     * email
     */
    private String email;
    /**
     * 0-普通用户; 1-超级管理员; 2-版主;
     */
    private Integer type;
    /**
     * 0-未激活; 1-已激活;
     */
    private Integer status;
    /**
     * activationCode
     */
    private String activationCode;
    /**
     * headerUrl
     */
    private String headerUrl;
    /**
     * createTime
     */
    private Date createTime;

}