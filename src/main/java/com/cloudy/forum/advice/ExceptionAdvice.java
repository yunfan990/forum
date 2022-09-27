package com.cloudy.forum.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author li bin
 * @version 1.0.0
 * @description
 * @date 2022-09-27 12:30
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {
    private static final Logger logger= LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class})
    public void handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException{
        logger.error("服务器发生了异常："+e.getMessage());
//        for (StackTraceElement element:e.getStackTrace()){
//            logger.error(element.toString());
//        }
        String xRequestWith=request.getHeader("x-requested-with");
        if("XMLHttpRequest".equals(xRequestWith)){
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer=response.getWriter();
            writer.write("信息有无");
        }else {
            response.sendRedirect(request.getContextPath()+"/error");
        }
    }
}
