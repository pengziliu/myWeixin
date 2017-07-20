package com.lzp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 返回纯文本，无需进行xml或者json解析
 * @author Administrator
 *
 */
public class NetUtil {
	// 发送一个request请求给服务器，基于HttpUrlConnection
	public static String sendDataByGetRequest(String address,
			Map<String, String> params, String encoding)
			throws UnsupportedEncodingException, IOException {
		StringBuilder sb = new StringBuilder(address);
		sb.append("?");

		// ?op=save&pname=xx&price=xxx
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append('=')
					.append(URLEncoder.encode(entry.getValue(), encoding))
					.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);

		// 接下来用url发送数据
		URL url = new URL(sb.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		if (con.getResponseCode() == 200) {
			InputStream iis = con.getInputStream();
			byte[] bs = readFromStream(iis);
			String message = new String(bs, encoding);
			return message;
		} else {
			return "查无此号码";
		}

	}

	// 发送一个request请求给服务器，基于HttpUrlConnection,不用拼接
	public static String sendDataByGetRequest2(String address, String encoding)
			throws UnsupportedEncodingException, IOException {
		StringBuilder sb = new StringBuilder(address);
		// 接下来用url发送数据
		URL url = new URL(sb.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		if (con.getResponseCode() == 200) {
			InputStream iis = con.getInputStream();
			byte[] bs = readFromStream(iis);
			String message = new String(bs, encoding);
			return message;
		} else {
			return "您输入的请求有误";
		}

	}

	// 发送一个request请求给服务器，基于HttpUrlConnection
	public static String sendDataByGetRequest3(String address, String params,
			String encoding) throws UnsupportedEncodingException, IOException {
		StringBuilder sb = new StringBuilder(address);
		sb.append(params);

		// 接下来用url发送数据
		URL url = new URL(sb.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		if (con.getResponseCode() == 200) {
			InputStream iis = con.getInputStream();
			byte[] bs = readFromStream(iis);
			String message = new String(bs, encoding);
			return message;
		} else {
			return "小j会努力学好语文的，争取下次能明白您说的话！O(∩_∩)O~~";
		}

	}

	private static byte[] readFromStream(InputStream iis) throws IOException {
		byte[] buffer = new byte[1024];
		int length = -1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((length = iis.read(buffer, 0, buffer.length)) != -1) {
			baos.write(buffer, 0, length);
		}
		iis.close();
		return baos.toByteArray();
	}

}