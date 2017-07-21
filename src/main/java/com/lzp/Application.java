package com.lzp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@EnableConfigurationProperties(MongoProperties.class)
//@ComponentScan(basePackages={"com.lzp"})
//@EnableAutoConfiguration
@SpringBootApplication
public class Application   {
//http://lzpwx.ngrok.cc/weixin/handle
	public static void main(String[] args)

	{
		SpringApplication.run(Application.class, args);
		System.out.println("==================springboot start==============");
	}



}
