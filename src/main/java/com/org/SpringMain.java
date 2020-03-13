package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.SpringServletContainerInitializer;


@SpringBootApplication

@EnableConfigurationProperties
public class SpringMain extends SpringBootServletInitializer 
{

	public static void main(String[] args)
	{
		System.setProperty("jasypt.encryptor.password", "MYENC");
		SpringApplication.run(SpringMain.class, args);
		System.out.println("hai man");
	}
}
