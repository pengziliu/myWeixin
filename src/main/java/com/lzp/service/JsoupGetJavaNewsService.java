package com.lzp.service;

import com.lzp.dao.ArticleModuleRepository;
import com.lzp.dao.ArticleRepository;
import com.lzp.dao.ArticleTagRepository;
import com.lzp.entity.ArticleModule;
import com.lzp.entity.ArticleTag;
import com.lzp.entity.Article_;
import com.lzp.util.HttpClientUtils;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liuzp on 2017/7/21.
 */
@Service
public class JsoupGetJavaNewsService {

    @Value("${java.nsws.url}")
    private String JAVA_NEWS_URL ;

    private org.slf4j.Logger logger = LoggerFactory
            .getLogger(this.getClass());
    
    private final String MS_BLOG_URL="http://blog.csdn.net/u013110200/article/details/55517717";

    @Autowired
    private ArticleModuleRepository articleModuleRepository;

    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private ArticleTagRepository articleTagRepository;
    
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



    public void getJavaNews(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArticleModule articleModule =  articleModuleRepository.findByName("JAVA");

        String result = HttpClientUtils.getContentFromUrl( JAVA_NEWS_URL);
        Document root_document = Jsoup.parse(result);
        Elements list = root_document.select(".line_list");
        Iterator<Element>  iterator =  list.iterator();
        while (iterator.hasNext()){
            Element element = iterator.next();
            //获取文章模块
            String title = element.select("a.tit_list").first().text();
            String titleDetail = element.select("a.tit_list").first().attr("href");
            if(articleRepository.findByTitle(title)!=null){
            	//已经存在  跳过
            	continue;
            }
            //去解析titleDetail里面对应的具体文章路径
            String linkUrl = getLinkUrl(titleDetail);  
            
            
            String from = element.select("div span a").first().text();
            String author = element.select("div span .user_name").first().text();
            boolean linkType =  true;
            
            String createDate = df.format(new Date());
            String articleModuleId = articleModule.getId();
            //获取标签
            Elements tags =  element.select("div span").get(1).getElementsByTag("a");
            List<String> tagList = new ArrayList<String>();
            Iterator<Element>  tagsIterator =  tags.iterator();
            while (tagsIterator.hasNext()){
            	Element tag = tagsIterator.next();
            	String tagName = tag.text();
            	if(articleTagRepository.findByName(tagName)!=null){
            		continue;
            	}
            	ArticleTag articleTag = new ArticleTag();
            	articleTag.setId(new ObjectId().toString());
            	articleTag.setArticleModuleId(articleModuleId);
            	articleTag.setName(tagName);
            	articleTagRepository.save(articleTag);
            	logger.info("从极客网站爬取到数据=标签为：{}",tagName);
            	tagList.add(articleTag.getId());
            }
            
            saveArticle(title, from, author, linkType, linkUrl, createDate, articleModuleId,tagList);
            logger.info("从极客网站爬取到数据=标题为：{}",title);
        }

    }

    
	private String getLinkUrl(String titleDetail) {
		 String result = HttpClientUtils.getContentFromUrl( titleDetail);
		 String linkUrl  = Jsoup.parse(result).getElementsByTag("a").select(".link_detail").first().text();
		 logger.info("从detail解析出来的文章的真实地址为:{}",linkUrl);
		 return linkUrl;
	}

	private Article_ saveArticle(String title, String from, String author, boolean linkType, String linkUrl,
			String createDate, String articleModuleId,List<String> tagList) {
		Article_ article_ = new Article_();
		article_.setArticleModuleId(articleModuleId);
		article_.setAuthor(author);
		article_.setFrom(from);
		article_.setCreateDate(createDate);
		article_.setTitle(title);
		article_.setClickNum(0);
		article_.setLinkType(linkType);
		article_.setLinkUrl(linkUrl);
		article_.setTagId(tagList.toArray(new String[tagList.size()]));
//		if(linkUrl.indexOf("blog.csdn.net")!=-1){
//			//如果是csdn的文章 根据链接将内容也爬过来
//			article_.setLinkType(false);
//			 String content = getCsdnArticleContentByUrl(linkUrl);
//		}
		articleRepository.save(article_);
		return article_;
	}

	/**
	 * 从别人的博客爬取面试题和答案并存到数据库
	 * @param MS_BLOG_URL
	 * @return
	 */
	public  String getMSContent() {
		 String result = HttpClientUtils.getContentFromUrl( MS_BLOG_URL);
		 Document root_document = Jsoup.parse(result);
		 Elements elist= root_document.select("div #article_content");
		 Iterator<Element>  iterator =  elist.iterator();
		 
		 
		 
		return null;
	}


}
