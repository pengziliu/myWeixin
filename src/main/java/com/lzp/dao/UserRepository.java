package com.lzp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lzp.entity.User;

/**
 * Created by liuzp on 2017/7/21.
 */
public interface UserRepository extends MongoRepository<User, String> {

	User findByName(String name);
}
