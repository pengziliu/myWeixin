package com.lzp.service;


import com.lzp.util.Constants;
import com.lzp.util.NetUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 手机归属地查询
 * @author Administrator
 *
 */
public class MobileService {
	public static String mobilePlace(String mobileCode){
		Map<String,String> map = new HashMap<String,String>();
		map.put("mobileCode", mobileCode);
		map.put("userID", "");

		String message="";
		try {
			message= NetUtil.sendDataByGetRequest(Constants.SENDPATH, map, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s= message.substring(0, message.lastIndexOf("<"));
		return s.substring(s.lastIndexOf(">")+1);
	};

	public static void main(String args[]){
		System.out.println("号码归属地："+mobilePlace("18692003840"));
	}
}
