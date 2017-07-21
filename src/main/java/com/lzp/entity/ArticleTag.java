package com.lzp.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by liuzp on 2017/7/21.
 */
public class ArticleTag {
    @Id
    private String id;

    //关联的模块id
    private String articleModuleId;

    //标签名称
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleModuleId() {
        return articleModuleId;
    }

    public void setArticleModuleId(String articleModuleId) {
        this.articleModuleId = articleModuleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
