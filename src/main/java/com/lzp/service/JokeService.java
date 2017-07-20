package com.lzp.service;//package com.lzp.service;
//

import com.lzp.util.Constants;
import com.lzp.util.NetUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class JokeService {
	public static String sayJoke(){
		String message="";
		try {
			message= NetUtil.sendDataByGetRequest2(Constants.SENDPATH2, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	public static void main(String args[]){
		System.out.println(sayJoke());
	}

}
