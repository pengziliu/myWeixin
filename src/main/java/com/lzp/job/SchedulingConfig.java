package com.lzp.job;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lzp.service.JsoupGetJavaNewsService;


/**
 * 定时任务配置类
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {

	private org.slf4j.Logger logger = LoggerFactory
            .getLogger(this.getClass());
	
	@Autowired
	private JsoupGetJavaNewsService jsoupGetJavaNewsService;
	

    @Scheduled(cron = "0 */30 * * * ?") // 每20秒执行一次
    public void scheduler() {
    	
        logger.info(">>>>>>>>>>>>> 爬取极客网站JAVA咨询定时任务启动 ... ");
        jsoupGetJavaNewsService.getJavaNews();
    }

}
