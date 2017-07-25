package com.lzp.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.lzp.bean.Page;
import com.lzp.entity.Questions;

@Service
public class QuestionsService extends BaseMongoService<Questions>{

	public Page list(Integer pageSize, Integer pageNum, String type) {
		// TODO Auto-generated method stub
		Query query =  new Query(Criteria.where("type").is(type));
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "createDate"));
		Page page = null;
    	try {
			 page =   lists(query, pageNum, pageSize, sort);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return page;
	}
	
	

}
