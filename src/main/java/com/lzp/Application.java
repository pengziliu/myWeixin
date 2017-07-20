package com.lzp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration    
@ComponentScan    
@EnableAutoConfiguration  
@SpringBootApplication
public class Application  extends SpringBootServletInitializer {
//http://lzpwx.ngrok.cc/weixin/handle
	public static void main(String[] args)

	{
		SpringApplication.run(Application.class, args);
		System.out.println("==================springboot start==============");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(Application.class);
	}


}
