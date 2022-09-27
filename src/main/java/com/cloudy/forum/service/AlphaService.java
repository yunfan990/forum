package com.cloudy.forum.service;

import com.cloudy.forum.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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

    @PostConstruct
    public void init(){
        System.out.println("初始化alphoservice");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁对象");
    }

    @Autowired
    private AlphaDao alphaDao;

    public String getName(){
        return alphaDao.select();
    }
}
