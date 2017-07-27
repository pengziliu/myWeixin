package com.lzp.dao;

import com.lzp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by liuzp on 2017/7/21.
 */
public interface UserRepository extends MongoRepository<User, String> {

	User findByUserName(String name);
}
