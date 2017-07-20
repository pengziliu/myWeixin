package com.lzp.bean;

import java.util.List;
import java.util.Map;

/**
 * 翻译服务bean
 * 
 * @author Administrator
 * 
 */
public class Weather {
	private String currentCity;// 当前城市
	private List<Map<String, String>> index;// 返回json中的index数组
	private List<Map<String, String>> weather_data;// 返回的json数据中的weather_data数组

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public List<Map<String, String>> getIndex() {
		return index;
	}

	public void setIndex(List<Map<String, String>> index) {
		this.index = index;
	}

	public List<Map<String, String>> getWeather_data() {
		return weather_data;
	}

	public void setWeather_data(List<Map<String, String>> weather_data) {
		this.weather_data = weather_data;
	}

}
