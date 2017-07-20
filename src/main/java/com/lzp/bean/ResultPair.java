package com.lzp.bean;

/**
 * 结果对，用于翻译服务
 * 
 * @author Administrator
 * 
 */

public class ResultPair {
	// 原文
	private String src;
	// 译文
	private String dst;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}
}
