package com.lzp.dao;

import com.lzp.entity.ArticleTag;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by liuzp on 2017/7/21.
 */
public  interface ArticleTagRepository extends MongoRepository<ArticleTag, String> {

    ArticleTag  findByName(String name);

}
