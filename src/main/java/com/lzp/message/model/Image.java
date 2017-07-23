package com.lzp.message.model;

/**
 * 图片model
 * 
 * 
 */
public class Image {
	// 媒体文件id
	private String MediaId;
	
	public Image(){
		
	}
	public Image(String MediaId){
		this.MediaId=MediaId;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
