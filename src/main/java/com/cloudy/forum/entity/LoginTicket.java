package com.cloudy.forum.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (login_ticket)实体类
 *
 * @author yun fan
 * @since 2022-09-28 00:55:46
 * @description 自动类
 */
public class LoginTicket implements Serializable {
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
     * ticket
     */
    private String ticket;
    /**
     * 0-有效; 1-无效;
     */
    private Integer status;
    /**
     * expired
     */
    private Date expired;

}