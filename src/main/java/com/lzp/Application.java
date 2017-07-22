package com.lzp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends  SpringBootServletInitializer {
//http://lzpwx.ngrok.cc/weixin/handle
	public static void main(String[] args)

	{
		//System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(Application.class, args);
		System.out.println("==================springboot start==============");
	}

	@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			// TODO Auto-generated method stub
		return builder.sources(Application.class);
		}


}
