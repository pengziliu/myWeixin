package com.lzp.message.model;

/**
 * 视频model
 * 
 * @author Administrator
 * 
 */
public class Video {
	// 媒体文件id
	private String mediaId;
	// 缩略图的媒体id
	private String ThumbMediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
