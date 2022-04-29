package com.learning.mongotemplate;

import com.learning.mongotemplate.controller.I18Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDataMongoTemplateApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringDataMongoTemplateApplication.class, args);

		I18Controller i18Controller = (I18Controller) applicationContext.getBean("i18Service");
		System.out.println(i18Controller.sayGreeting());
	}

}
