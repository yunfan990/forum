package com.cloudy.forum.controller;

import com.cloudy.forum.annotation.LoginRequired;
import com.cloudy.forum.entity.User;
import com.cloudy.forum.service.UserService;
import com.cloudy.forum.until.ForumUtil;
import com.cloudy.forum.until.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Target;

/**
 * @author li bin
 * @version 1.0.0
 * @description
 * @date 2022-09-30 12:39
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Value("${forum.path.upload}")
    private String uploadPath;
    @Value("${forum.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;

    @LoginRequired
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage(){
        return  "/site/setting";
    }

    //上传图片
    @LoginRequired
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model){
        if(headerImage==null){
            model.addAttribute("error","请选择图片");
            return "/site/setting";
        }
        String fileName= headerImage.getOriginalFilename();
        String suffix= fileName.substring(fileName.lastIndexOf("."));
        if(StringUtils.isBlank(suffix)){
            model.addAttribute("error","文件格式有误");
            return "/site/setting";
        }
        fileName= ForumUtil.generateUUID()+suffix;
        File dest=new File(uploadPath+"/"+fileName);
        try {
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败："+e.getMessage());
            throw new RuntimeException("上传文件失败，服务器发生异常！",e);
        }
        //更新当前用户头像的路径（web）
        User user=hostHolder.getUser();
        String header=domain+contextPath+"/user/header/"+fileName;
        userService.updateHeader(user.getId(),header);
        return "redirect:/index";
    }

    @RequestMapping(path = "/header/{fileName}",method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response){
        fileName = uploadPath + "/" + fileName;
        String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
        response.setContentType("image/"+suffix);
        try(FileInputStream fils= new FileInputStream(fileName))
        {
            OutputStream os= response.getOutputStream();
            byte[] buffer=new byte[1024]; //声明 1024缓冲区
            int b=0;//游标
            while ((b=fils.read(buffer))!=-1){
                os.write(buffer,0,b);
            }
        }catch (IOException e){
            logger.error("读取头像失败:"+e.getMessage());
        }

    }
}
