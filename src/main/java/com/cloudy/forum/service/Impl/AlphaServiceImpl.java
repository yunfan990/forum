package com.cloudy.forum.service.Impl;

import com.cloudy.forum.dao.AlphaDao;
import com.cloudy.forum.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @auther li bin
 * @create 2022-09-27 23:29
 */
@Service
public class AlphaServiceImpl implements AlphaService {

    public AlphaServiceImpl(){
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

    @Override
    public String getName() {
        return alphaDao.select();
    }
}
