package com.lzp.service;

import com.lzp.dao.ArticleModuleRepository;
import com.lzp.entity.ArticleModule;
import com.lzp.util.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Created by liuzp on 2017/7/21.
 */
@Service
public class JsoupGetJavaNewsService {

    @Value("${java.nsws.url}")
    private String JAVA_NEWS_URL ;

    private org.slf4j.Logger logger = LoggerFactory
            .getLogger(this.getClass());


    private ArticleModuleRepository articleModuleRepository;

    /**
     * 获取远程地址的网页源数据
     * @param remoteUrl
     * @return
     */

    public String getHtmlSource(String remoteUrl){
        String content = HttpClientUtils.getContentFromUrl( remoteUrl);
        logger.info(content);
        return content;
    }

    public static void main(String args[]){
        JsoupGetJavaNewsService c = new JsoupGetJavaNewsService();
        String content = c.getHtmlSource("http://www.csdn.net/tag/java/news");
        System.out.print(content);
    }



    public void getJavaNsws(){

        ArticleModule articleModule =  articleModuleRepository.findByName("JAVA");

        String result = HttpClientUtils.getContentFromUrl( JAVA_NEWS_URL);
        Document root_document = Jsoup.parse(result);
        Elements list = root_document.select(".line_list");
        Iterator<Element>  iterator =  list.iterator();
        while (iterator.hasNext()){
            Element element = iterator.next();
            //获取文章模块
            //取5条
            String content = element.getElementsByTag("a").text();

        }

    }


}
