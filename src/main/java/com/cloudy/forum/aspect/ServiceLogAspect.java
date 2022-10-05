package com.cloudy.forum.aspect;

import com.cloudy.forum.until.GsonUtil;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * @author li bin
 * @version 1.0.0
 * @description
 * @date 2022-09-27 12:11
 */
@Component
@Aspect
public class ServiceLogAspect {

    private static final Logger logger= LoggerFactory.getLogger(ServiceLogAspect.class);
    /**
     * 开始时间
     */
    private long startTime = 0L;
    @Pointcut("execution(* com.cloudy.forum.controller.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        //用户【ip】在【】，调用了方法【】
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attributes.getRequest();
        String ip = request.getRemoteHost();

        String now =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String target= joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        logger.info(String.format("用户[%s]在[%s],访问了[%s]",ip,now,target));

    }
    @Around("pointcut()")
    public Object doArount(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        String ip = IpUtil.getIpAddr(request);
        String ip = request.getRemoteHost();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        List<Object> arguments = new ArrayList();
        if (proceedingJoinPoint.getArgs() != null && proceedingJoinPoint.getArgs().length > 0) {
            for (int i = 0; i < proceedingJoinPoint.getArgs().length; i++) {
                //这里出现参数不能转换问题，想了半天最后发现里面有request对象不能被序列化
                //最后把这些对象过滤了。
//                params += JSON.toJSON(joinPoint.getArgs()[i]) + ";";
                if (proceedingJoinPoint.getArgs()[i] instanceof ServletRequest || proceedingJoinPoint.getArgs()[i] instanceof ServletResponse || proceedingJoinPoint.getArgs()[i] instanceof MultipartFile) {
                    //HttpServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException:
                    // It is illegal to call this method if the current request is not in asynchronous mode
                    // (i.e. isAsyncStarted() returns false)
                    continue;
                }
                arguments.add(proceedingJoinPoint.getArgs()[i]);
            }
        }
        if (!arguments.isEmpty()) {
            try {
                //这里有个很严重的问题，因为JSON序列化有问题，所以这里会报错，改为Google 的Gson类来转换即可
                params += GsonUtil.GsonString(arguments);
            } catch (Exception e) {
                params += arguments.toString();
            }
        }


        //  获取请求头
        String headerContext = "";
        Enumeration headerNames = request.getHeaderNames();
        //使用循环遍历请求头，并通过getHeader()方法获取一个指定名称的头字段
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            if (headerContext.isEmpty()) {
                headerContext += " " + headerName + "： " + request.getHeader(headerName);
            } else {
                headerContext += "\r\n ┃           " + headerName + "： " + request.getHeader(headerName);
            }
        }

        Object result = null;
        result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis() - startTime;

        String logContext = "\r\n  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
        logContext += "\r\n ┃ 请求发起";
        logContext += "\r\n ┃ 接口： " + proceedingJoinPoint.getTarget().getClass().getName() + "." + proceedingJoinPoint.getSignature().getName();
        logContext += "\r\n ┃ 请求方式： " + request.getMethod();
        logContext += "\r\n ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
        logContext += "\r\n ┃ 请求头： " + headerContext;
        logContext += "\r\n ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
        if (!params.isEmpty()) {
            logContext += "\r\n ┃ 请求体： " + params;
            logContext += "\r\n ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
        }
        logContext += "\r\n ┃ 出参： " + result;
        logContext += "\r\n ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
        logContext += "\r\n ┃ 请求结束，接口耗时： " + endTime;
        logContext += "\r\n  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

        //logger.info(logContext);

        return result;

    }
}
