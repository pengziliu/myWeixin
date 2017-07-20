package com.lzp.message.resp;


import com.lzp.message.model.Music;

public class MusicMessage extends BaseMessage {
	// 音乐
	private  com.lzp.message.model.Music Music;

    public com.lzp.message.model.Music getMusic() {
        return Music;
    }

    public void setMusic(com.lzp.message.model.Music music) {
        Music = music;
    }
}
