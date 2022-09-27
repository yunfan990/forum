package com.cloudy.forum.controller;

import com.cloudy.forum.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("hello")
    @ResponseBody
    public String sayHello(){
        String n= alphaService.getName();
        return "hello world!"+n;
    }
}
