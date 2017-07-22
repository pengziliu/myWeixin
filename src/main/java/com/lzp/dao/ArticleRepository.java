package com.lzp.dao;

import com.lzp.entity.Article_;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by liuzp on 2017/7/21.
 */
public  interface ArticleRepository extends MongoRepository<Article_, String> {

    Article_  findByTitle(String title);

}
