package com.cloudy.forum.controller;

import com.cloudy.forum.dao.DiscussPostMapper;
import com.cloudy.forum.dao.UserMapper;
import com.cloudy.forum.entity.DiscussPost;
import com.cloudy.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther li bin
 * @create 2022-09-28 23:50
 */
@Controller
public class HomeController {
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model){
       List<DiscussPost> list= discussPostMapper.selectDiscussPosts(0,0,10);
       List<Map<String,Object>> discussposts=new ArrayList<>();
       if(list!=null){
           for (DiscussPost post:list){
             Map<String, Object> map=new HashMap<>();
             map.put("post",post);
             User user= userMapper.selectUserById(post.getUserId());
             map.put("user",user);

             discussposts.add(map);
           }
       }
       model.addAttribute("discussPosts",discussposts);
       return "/index";
    }
}
