package com.boman.upms.rpc.factory;

import java.util.TimerTask;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.SocketIOClient;
import com.boman.common.util.StringUtil;
import com.boman.upms.dao.model.MessageText;
import com.boman.upms.rpc.constant.MessageConstant;

public class AsyncFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncFactory.class);
	
	
	/**
	 * 把消息通知发送给用户
	 */
	public static TimerTask recordOper(final MessageText messageText) {	
		return new TimerTask() {			
			@Override
			public void run() {
				try {
					SocketIOClient client = (SocketIOClient) MessageConstant.clientsMap.get(messageText.getRectName());
					SocketIOClient clientsAndroid = (SocketIOClient) MessageConstant.clientsAndroidMap.get(messageText.getRectName());
					if(client != null || StringUtil.isActivity(messageText.getRectName())){
						//LOGGER.info("TimerTask recordOper ==> SocketIOClient is null");
						LOGGER.info("Send {} a message",messageText.getRectName());
						client.sendEvent("advert_info", messageText);
						//return;
					}
					if(clientsAndroid != null || StringUtil.isActivity(messageText.getRectName())){
						//LOGGER.info("TimerTask recordOper ==> SocketIOClient is null");
						LOGGER.info("Send Android {} a message",messageText.getRectName());
						clientsAndroid.sendEvent("advert_info", messageText);
						//return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}			
}