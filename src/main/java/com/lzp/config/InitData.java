package com.lzp.config;

import com.lzp.dao.ArticleModuleRepository;
import com.lzp.dao.QuestionsRepositoty;
import com.lzp.entity.ArticleModule;
import com.lzp.service.JsoupGetJavaNewsService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by liuzp on 2017/7/21.
 */
@Component
public class InitData implements CommandLineRunner {

    private org.slf4j.Logger logger = LoggerFactory
            .getLogger(this.getClass());

    @Autowired
    private ArticleModuleRepository articleModuleRepository;
    
    @Autowired
    private QuestionsRepositoty questionsRepositoty;

    @Autowired
    private JsoupGetJavaNewsService jsoupGetJavaNewsService;
    
    @Override
    public void run(String... strings) throws Exception {

        ArticleModule articleModule = articleModuleRepository.findByName("JAVA");
        if (articleModule == null) {
            logger.info("########系统初始化开始############");
            articleModule = new ArticleModule();
            articleModule.setName("JAVA");
            articleModuleRepository.save(articleModule);
        }
        if(questionsRepositoty.count()==0){
        	//开始从网站爬取出java面试题并插入数据库
        	jsoupGetJavaNewsService.getMSContent();
        }


    }
}
