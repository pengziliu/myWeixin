package com.lzp.service;


import com.lzp.message.resp.NewsMessage;

import java.io.InputStream;
import java.util.List;

/**
 * 周边检索
 * 
 * @author Administrator
 * 
 */
public class SimService {
	/*public static NewsMessage searchSim(String location, String keyWord) {
		// 周边检索地址
		String requestUrl = Constants.SENDPATH10;
		//如果关键字为空，则默认搜索周边美食
		if (keyWord == null || "".equals(keyWord)) {
			keyWord = "美食";
		}
		// 对地理位置和周边关键字进行编码
		requestUrl = requestUrl.replace("LOCATION",
				HttpRequestUtil.urlEncodeUTF8(location));
		requestUrl = requestUrl.replace("KEYWORD",
				HttpRequestUtil.urlEncodeUTF8(keyWord));
		// 处理空格
		requestUrl = requestUrl.replaceAll("\\+", "%20");

		// 查询并获取返回结果
		InputStream inputStream = HttpRequestUtil
				.httpRequestToInputStream(requestUrl);
		// 从返回的结果中解析出检索信息
		NewsMessage newsMessage = parseMusic(inputStream);

		return newsMessage;

	}
	*//**
	 * 解析周边参数
	 * @param inputStream
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	private static NewsMessage parseMusic(InputStream inputStream) {
		NewsMessage NewsMessage = null;
		
		try {
			//使用dom4j解析xml字符串
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			//得到xml根元素
			Element root = document.getRootElement();
			//count表示搜到的结果数
			String count = root.element("count").getText();
			//当搜索到的结果数大于0时
			if(!"0".equals(count)){
				//结果
				Element poiList = root.element("poiList");
				List<Element> point = poiList.elements("point");
				
				//逐个将point存到Map里面
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return NewsMessage;
	}
	
	//测试方法
	public static void main(String[] args){
		NewsMessage newsMessage=searchSim("116.305145,39.982368", null);
		
	}*/
}
