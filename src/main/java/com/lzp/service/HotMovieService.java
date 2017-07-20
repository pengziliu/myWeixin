package com.lzp.service;

import java.io.UnsupportedEncodingException;

import com.lzp.bean.Movie;
import com.lzp.message.model.Article;
import com.lzp.util.Constants;
import com.lzp.util.HttpRequestUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class HotMovieService {
    
    public static Movie movieDetect(String place){
    	Movie movie=new Movie();
    	//热门影片查询地址
    	String queryUrl= Constants.SENDPATH5;
    	
    	//对url进行编码
		try {
			queryUrl = queryUrl.replace("LOCATION", java.net.URLEncoder.encode(place, "UTF-8"));
			
			//调用热门影片查询接口
			String json = HttpRequestUtil.httpRequest(queryUrl);
			//解析返回的json
			JSONObject jsonObj=JSONObject.fromObject(json);
			JSONObject results=jsonObj.getJSONObject("result");
			//System.out.println(result);
			
			JSONArray movieInfo=results.getJSONArray("movie");
			
			JSONObject result =movieInfo.getJSONObject(0);
			movie.setMovie_name(result.getString("movie_name"));
			movie.setMovie_type(result.getString("movie_type"));
			movie.setMovie_release_date(result.getString("movie_release_date"));
			movie.setMovie_nation(result.getString("movie_nation"));
			movie.setMovie_starring(result.getString("movie_starring"));
			movie.setMovie_length(result.getString("movie_length"));
			movie.setMovie_picture(result.getString("movie_picture"));
			movie.setMovie_score(result.getString("movie_score"));
			movie.setMovie_director(result.getString("movie_director"));
			movie.setMovie_tags(result.getString("movie_tags"));
			movie.setMovie_message(result.getString("movie_message"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	return movie;
    }
    
    //构建单图文回送消息
    public static Article hotMovieMessage(String place){
    	Movie movie=new Movie();
    	movie=movieDetect(place);
    	Article article1 = new Article();
        article1.setTitle("地区热门影片：  "+movie.getMovie_name());  
        article1.setDescription("影片类型："+movie.getMovie_type()+"\n"
        		+"上映日期："+movie.getMovie_release_date()+"\n"
        		+"上映地区："+movie.getMovie_nation()+"\n"
        		+"演员表："+movie.getMovie_starring()+"\n"
        		+"放映时长："+movie.getMovie_length()+"\n"
        		+"影片评分："+movie.getMovie_score()+"\n"
        		+"导演："+movie.getMovie_director()+"\n"
        		+"影片分类："+movie.getMovie_tags()+"\n"
        		+"影片简介："+movie.getMovie_message());  
        article1.setPicUrl(movie.getMovie_picture());  
        article1.setUrl("");
        return article1;
    }
    
    public static void main(String args[]){
    	movieDetect("长沙");
    	System.out.println(movieDetect("长沙").getMovie_name());
    	System.out.println(movieDetect("长沙").getMovie_message());
    }
}
