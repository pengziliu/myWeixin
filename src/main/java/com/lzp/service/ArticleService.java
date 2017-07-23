package com.lzp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.lzp.bean.Page;
import com.lzp.dao.ArticleModuleRepository;
import com.lzp.entity.ArticleModule;
import com.lzp.entity.Article_;

@Service
public class ArticleService extends BaseMongoService<Article_> {
	
	@Autowired
	private ArticleModuleRepository articleModuleRepository; 
	

	public Page listArticle(Integer pageSize, Integer pageNum, String moduleName) {
		ArticleModule articleModule   = articleModuleRepository.findByName(moduleName);
    	Query query =  new Query(Criteria.where("articleModuleId").is(articleModule.getId()));
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
