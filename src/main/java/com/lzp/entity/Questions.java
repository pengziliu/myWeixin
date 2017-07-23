package com.lzp.entity;

import org.springframework.data.annotation.Id;

public class Questions {
	
    @Id
	 private String id;
    
     private String createDate;
    
    //标题
     private String title;
    
    //答案
     private String answer;
     
     //分类
     private String type;

}
