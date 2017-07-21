package com.lzp.dao;

import com.lzp.entity.ArticleModule;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by liuzp on 2017/7/21.
 */
public  interface ArticleModuleRepository extends MongoRepository<ArticleModule, String> {

    ArticleModule  findByName(String name);

}
