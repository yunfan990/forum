package com.cloudy.forum.service;

import org.springframework.stereotype.Service;

/**
 * @author li bin
 * @version 1.0.0
 * @description
 * @date 2022-09-27 13:22
 */
@Service
public class AlphaService {
    public AlphaService(){
        System.out.println("构造器实例化方法");
    }

    public String getName(){
        return "12";
    }
}
