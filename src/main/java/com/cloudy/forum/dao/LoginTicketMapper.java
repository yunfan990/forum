package com.cloudy.forum.dao;

import com.cloudy.forum.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * (login_ticket)数据Mapper
 *
 * @author yun fan
 * @since 2022-09-28 00:53:37
 * @description 自动类
*/
@Mapper
public interface LoginTicketMapper{
    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    @Update({
            "<script>",
            "update login_ticket set status=#{status} where ticket=#{ticket} ",
            "<if test=\"ticket!=null\"> ",
            "and 1=1 ",
            "</if>",
            "</script>"
    })
    int updateStatus(String ticket, int status);
}
