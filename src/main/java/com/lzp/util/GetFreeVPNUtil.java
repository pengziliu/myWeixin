package com.lzp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取一小时免费VPN
 * @author fuliwanglong
 *
 */
public class GetFreeVPNUtil {
	
	
	/**
	 * 主方法
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public static  String getfreeVpn() throws UnsupportedEncodingException, IOException{
		
		String strURL = "http://45.79.92.249/mm.txt";  
	    URL url = new URL(strURL);  
	    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();  
	    InputStreamReader input = new InputStreamReader(httpConn  
	            .getInputStream(), "utf-8");  
	    BufferedReader bufReader = new BufferedReader(input);  
	    String line = "";   
	    StringBuilder contentBuf = new StringBuilder();  
	    while ((line = bufReader.readLine()) != null) {  
	        contentBuf.append(line);  
	    }  
	    String buf = contentBuf.toString();  
//	    int beginIx = buf.indexOf("查询结果[");  
//	    int endIx = buf.indexOf("上面四项依次显示的是");  
//	    String result = buf.substring(beginIx, endIx);  
		return buf;
	}
}
