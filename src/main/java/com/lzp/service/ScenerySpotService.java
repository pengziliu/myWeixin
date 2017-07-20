package com.lzp.service;

import com.lzp.bean.ScenerySpot;
import com.lzp.message.model.Article;
import com.lzp.util.Constants;
import com.lzp.util.HttpRequestUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenerySpotService {

    public static ScenerySpot scenerySpotDetect(String name){
    	ScenerySpot scenerySpot=new ScenerySpot();
    	//景点查询地址
    	String queryUrl= Constants.SENDPATH6;

    	//对url进行编码
    	try {
			queryUrl = queryUrl.replace("ID", java.net.URLEncoder.encode(name, "UTF-8"));

			//调用景点查询接口
			String json = HttpRequestUtil.httpRequest(queryUrl);
			//解析返回的json
			JSONObject jsonObj=JSONObject.fromObject(json);
			JSONObject results=jsonObj.getJSONObject("result");
			//System.out.println(results);
			//System.out.println(results.getString("name"));

			scenerySpot.setName(results.getString("name"));
			scenerySpot.setTelephone(results.getString("telephone"));
			scenerySpot.setStar(results.getString("star"));
			scenerySpot.setUrl(results.getString("url"));
			scenerySpot.setAbstracts(results.getString("abstract"));
			scenerySpot.setDescription(results.getString("description"));

			//System.out.println(scenerySpot.getDescription());
			JSONObject ticket_info=results.getJSONObject("ticket_info");

			//System.out.println(ticket_info);
			scenerySpot.setPrice(ticket_info.getString("price"));
			scenerySpot.setOpen_time(ticket_info.getString("open_time"));

			//System.out.println(scenerySpot.getOpen_time());
			JSONArray attention=ticket_info.getJSONArray("attention");
			//System.out.println(attention);

			List<Map<String,String>> attrntionList=new ArrayList<Map<String,String>>();
			for(int i=0;i<attention.size();i++){
				JSONObject info=attention.getJSONObject(i);
				Map<String,String> map=new HashMap<String,String>();
				map.put("name", info.getString("name"));
				map.put("description", info.getString("description"));

				attrntionList.add(map);
			}
			scenerySpot.setAttention(attrntionList);

//			System.out.println(scenerySpot.getAttention());
			System.out.println(scenerySpot.getAttention().get(0).get("name"));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

    	return scenerySpot;
    }

    //构建单图文回送消息
    public static Article scenerySpotMessage(String name){
    	ScenerySpot scenerySpot=new ScenerySpot();
    	scenerySpot=scenerySpotDetect(name);
    	//循环，拼接attention
    	String att="";
    	List<Map<String,String>> attention=scenerySpot.getAttention();
    	if(attention.size()>0){
    		for(int i=0;i<attention.size();i++){
        		att+=attention.get(i).get("name")+"\n"+attention.get(i).get("description")+"\n";
        	}
    	}

    	Article article1 = new Article();
        article1.setTitle("景点介绍："+scenerySpot.getName());
        article1.setDescription("订票热线："+scenerySpot.getTelephone()+"\n"
        		+"星级："+scenerySpot.getStar()+"\n"
        		+"摘要："+scenerySpot.getAbstracts()+"\n"
        		+"简介："+scenerySpot.getDescription()+"\n"
        		+"票价："+scenerySpot.getPrice()+"\n"
        		+"开放时间："+scenerySpot.getOpen_time()+"\n"
        		+att+"\n"
        		+"小编友情提示：直接点击文章进入该景点的微官网！");
        article1.setPicUrl("");
        article1.setUrl(scenerySpot.getUrl());
        return article1;
    }

    public static void main(String args[]){
    	scenerySpotDetect("yiheyuan");
    }
}
