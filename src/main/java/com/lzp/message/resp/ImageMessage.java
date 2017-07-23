package com.lzp.message.resp;


/**
 * 图片消息
 *
 * @author Administrator
 *
 */
public class ImageMessage extends BaseMessage {
	// 图片/**/
	private com.lzp.message.model.Image Image;

	public com.lzp.message.model.Image getImage() {
		return Image;
	}

	public void setImage(com.lzp.message.model.Image image) {
		Image = image;
	}

}
