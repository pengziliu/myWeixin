package com.lzp.dao;

import com.lzp.entity.UserTest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by liuzp on 2017/7/21.
 */
public interface UserRepository extends MongoRepository<UserTest, String> {

    UserTest findByName(String name);
}
