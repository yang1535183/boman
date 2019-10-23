package com.boman.common.util.wechat.wechat.message.req;
/**
 * 音频消息
 */
public class VoiceMessage extends BaseMessage{
	//媒体ID
	private String mediaId;
	//语音格式
	private String Format;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	
}
