package com.lzp.event;

/**
 * 扫描带参数二维码事件
 * 
 * @author Administrator
 * 
 */
public class QRCodeEvent extends BaseEvent {
	// 事件key值
	private String EventKey;
	// 用于换取二维码图片
	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

}
