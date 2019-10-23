package com.boman.upms.rpc.constant;

import java.util.HashMap;
import java.util.Map;

import com.corundumstudio.socketio.SocketIOClient;

public class MessageConstant {
	
	public static Map<String, SocketIOClient> clientsMap = new HashMap<String, SocketIOClient>();

	public static Map<String, SocketIOClient> clientsAndroidMap = new HashMap<String, SocketIOClient>();
	
}
