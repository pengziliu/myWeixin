package com.lzp.bean;

import java.util.List;
import java.util.Map;

/**
 * 景点查询服务bean
 * 
 * @author Administrator
 * 
 */
public class ScenerySpot {
	private String name;// 景点名
	private String telephone;// 订票热线
	private String star;// 景点星级
	private String url;// 景点网址
	private String abstracts;// 摘要
	private String description;// 描叙
	private String price;// 票价
	private String open_time;// 开放时间
	private List<Map<String, String>> attention;// 更多注意事项

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public List<Map<String, String>> getAttention() {
		return attention;
	}

	public void setAttention(List<Map<String, String>> attention) {
		this.attention = attention;
	}
}
