package com.cloudy.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {

		SpringApplication.run(ForumApplication.class, args);
		System.out.println("服务起动成功");
	}

}
