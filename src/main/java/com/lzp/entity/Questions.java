package com.lzp.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Questions implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 private String id;
    
     private String createDate;
    
    //标题
     private String title;
    
    //答案
     private String answer;
     
     //分类
     private String type;//java js

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
     
     

}
