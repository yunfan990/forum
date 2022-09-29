package com.cloudy.forum;

import com.cloudy.forum.dao.AlphaDao;
import com.cloudy.forum.until.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@ContextConfiguration(classes = ForumApplication.class)
public class ForumApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Test
	public void testApplication(){
		System.out.println(applicationContext);
	}

	@Autowired
	@Qualifier("alphahiberano")
	private AlphaDao alphaDao;

	@Test
	public void testAutoWired(){
		System.out.println(alphaDao);
	}

	@Autowired
	private MailClient sendMail;
	@Autowired
	private TemplateEngine templateEngine;
	@Test
	public void testMail(){
		//sendMail.SendMail("939358140@qq.com","TEST","测试内容");
		Context context=new Context();
		context.setVariable("username","sunday");
		String content= templateEngine.process("/mail/demo",context);
		System.out.println(content);
		sendMail.sendMail("939358140@qq.com","HTML",content);











	}
}
