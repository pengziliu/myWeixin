package com.lzp.message.req;

/**
 * 文本消息，继承自消息基类
 * 
 * @author Administrator
 * 
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
