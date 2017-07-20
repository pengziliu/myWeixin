package com.lzp.message.resp;


import com.lzp.message.model.Voice;

/**
 * 语音消息
 * 
 * @author Administrator
 * 
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}
