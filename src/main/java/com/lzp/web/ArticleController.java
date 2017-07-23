package com.lzp.web;

import com.lzp.bean.Page;
import com.lzp.dao.ArticleModuleRepository;
import com.lzp.entity.ArticleModule;
import com.lzp.entity.Article_;
import com.lzp.service.ArticleService;
import com.lzp.service.JsoupGetJavaNewsService;
import com.lzp.util.ParamUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzp on 2017/7/21.
 */
@RestController
public class ArticleController {

    private org.slf4j.Logger log = LoggerFactory
            .getLogger(this.getClass());

    @Autowired
    private JsoupGetJavaNewsService jsoupGetJavaNewsService;

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ArticleModuleRepository articleModuleRepository;

    @RequestMapping("/getJavaNews")
    public String getJavaNews() {

        jsoupGetJavaNewsService.getJavaNews();
        
        return "Hello World";
    }

    /**
     * 分页获取文章
     * @param pageSize
     * @param pageNum
     * @param moduleName
     * @return
     */
    @RequestMapping("/listArticle")
    public Page listArticle(Integer pageSize,Integer pageNum,String moduleName ) {
    	ParamUtils.notNull(moduleName);
    	return articleService.listArticle(pageSize,pageNum,moduleName);
        
    }
    /**
     * 根据唯一索引获取文章
     * @param id
     * @return
     */
    @RequestMapping(value =  "/article/get",method=RequestMethod.GET)
    public Article_ getById(String id ){
    	if(StringUtils.isEmpty(id)){
    		return null;
    	}
    	return articleService.getById(id);
    	
    }


}
