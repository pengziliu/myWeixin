package com.lzp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lzp.entity.Questions;

public interface QuestionsRepositoty extends MongoRepository<Questions,String>{
	
	
	Questions findByTitle(String title);
	
}
