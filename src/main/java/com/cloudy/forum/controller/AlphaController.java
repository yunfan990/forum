package com.cloudy.forum.controller;

import com.cloudy.forum.dao.DiscussPostMapper;
import com.cloudy.forum.entity.DiscussPost;
import com.cloudy.forum.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author li bin
 * @version 1.0.0
 * @description
 * @date 2022-09-27 12:05
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @RequestMapping("hello")
    @ResponseBody
    public String sayHello(){

        StringBuilder str=new StringBuilder();
        int rows= discussPostMapper.selectDiscussPostRows(0);
        List<DiscussPost> list= discussPostMapper.selectDiscussPosts(0,0,10);
        for (DiscussPost post:list){
            str.append("用户ID:"+post.getUserId()+",");
        }
        return str.toString();
    }
}
