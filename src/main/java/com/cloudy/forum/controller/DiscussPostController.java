package com.cloudy.forum.controller;

import com.cloudy.forum.entity.Comment;
import com.cloudy.forum.entity.DiscussPost;
import com.cloudy.forum.entity.User;
import com.cloudy.forum.entity.dto.Page;
import com.cloudy.forum.service.CommentService;
import com.cloudy.forum.service.DiscussPostService;
import com.cloudy.forum.service.UserService;
import com.cloudy.forum.until.ForumConstant;
import com.cloudy.forum.until.ForumUtil;
import com.cloudy.forum.until.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static com.cloudy.forum.until.ForumConstant.ENTITY_TYPE_COMMENT;


/**
 * @auther li bin
 * @create 2022-10-01 1:00
 */
@Controller
@RequestMapping("/discuss")
public class DiscussPostController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String AddDiscussPost(String title,String content){
        User user=hostHolder.getUser();
        if(user==null){
            return ForumUtil.getJSONString(403,"请先登录后操作");
        }
        DiscussPost post=new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);
        //报错的地地方统一处理
        return ForumUtil.getJSONString(0,"发布成功");
    }

    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page){
        DiscussPost post = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post",post);
        //作者
        User user=userService.findUserById(post.getUserId());
        model.addAttribute("user",user);

        //评论信息
        page.setLimit(10);
        page.setPath("/discuss/detail/"+discussPostId);
        page.setRows(post.getCommentCount());
        List<Comment> commentList= commentService.findCommentsByEntity(ForumConstant.ENTITY_TYPE_POST,post.getId(),page.getOffset(), page.getLimit());
        List<Map<String, Object>> commentVoList=new ArrayList<>();
        for(Comment item:commentList){
            Map<String, Object> commentVo=new HashMap<>();
            commentVo.put("comment", item);
            commentVo.put("user", userService.findUserById(item.getUserId()));
            //查询回复
            List<Map<String,Object>> replyVoList=new ArrayList<>();
            List<Comment> replyList = commentService.findCommentsByEntity(ENTITY_TYPE_COMMENT, item.getId(), 0, Integer.MAX_VALUE);
            if(replyList!=null){

                for(Comment reply: replyList){
                    Map<String, Object> replyVo=new HashMap<>();
                    replyVo.put("reply", reply);
                    replyVo.put("user", userService.findUserById(reply.getUserId()));
                    User target= reply.getTargetId()==0?null:userService.findUserById(reply.getTargetId());
                    replyVo.put("target", target);
                    replyVoList.add(replyVo);
                }
            }
            commentVo.put("replys",replyVoList);

            //回复数量
            int replyCount= commentService.findCommentCount(ENTITY_TYPE_COMMENT,item.getId());
            commentVo.put("replayCount",replyCount);
            commentVoList.add(commentVo);
        }

        model.addAttribute("comments",commentVoList);
        return "/site/discuss-detail";
    }


}
