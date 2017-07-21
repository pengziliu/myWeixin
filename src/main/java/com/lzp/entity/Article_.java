package com.lzp.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by liuzp on 2017/7/21.
 */
public class Article_ {

    @Id
    private String id;

    //关联的模块id
    private String articleModuleId;

    //标题
    private String title;
    //作者
    private String author;
    //创建日期
    private String createDate;
    //内容
    private String content;
    //链接类型  true为外链
    private boolean  linkType;
    //点击次数
    private long clickNum;
    //如果是外链，则这个字段有值
    private String linkUrl;
    //来源
    private String from;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isLinkType() {
        return linkType;
    }

    public void setLinkType(boolean linkType) {
        this.linkType = linkType;
    }

    public long getClickNum() {
        return clickNum;
    }

    public void setClickNum(long clickNum) {
        this.clickNum = clickNum;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getArticleModuleId() {
        return articleModuleId;
    }

    public void setArticleModuleId(String articleModuleId) {
        this.articleModuleId = articleModuleId;
    }
}
