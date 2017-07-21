package com.lzp.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by liuzp on 2017/7/21.
 * 文章板块
 */
public class ArticleModule {
    @Id
    private String id;

    //板块名称
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
