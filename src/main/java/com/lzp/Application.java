package com.lzp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application   {
//http://lzpwx.ngrok.cc/weixin/handle
	public static void main(String[] args)

	{
		//System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(Application.class, args);

		System.out.println("==================springboot start==============");
	}



}
