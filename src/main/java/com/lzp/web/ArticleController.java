package com.lzp.web;

import com.lzp.service.JsoupGetJavaNewsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @RequestMapping("/getJavaNews")
    public String getJavaNews() {

        jsoupGetJavaNewsService.getJavaNews();
        
        return "Hello World";
    }



}
