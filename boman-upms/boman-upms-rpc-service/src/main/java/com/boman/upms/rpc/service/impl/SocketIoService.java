package com.boman.upms.rpc.service.impl;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.boman.common.util.RedisUtil;
import com.boman.upms.rpc.constant.MessageConstant;

@Component
public class SocketIoService {
	private static final Logger logger = LoggerFactory.getLogger(SocketIoService.class);
	
	private static SocketIOServer server;
	
	private static final String socketClientsKey = "socketClientsKey";
	
	public SocketIoService() {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					startServer();
					logger.info("SocketIOServer启动成功！");
				} catch (Exception e) {
					logger.info("初始化。。。。。io,{}",e.getMessage());
				}				
			}
		}).start();
	}
	
	public void startServer() throws InterruptedException {
		Configuration config = new Configuration();
		config.setHostname("0.0.0.0");
		config.setPort(40000);
		config.setMaxFramePayloadLength(1024 * 1024);
		config.setMaxHttpContentLength(1024 * 1024);
		config.setAuthorizationListener(new AuthorizationListener() {
			
			@Override
			public boolean isAuthorized(HandshakeData data) {
				String username = data.getSingleUrlParam("username");
				return true;
			}
		});
		
		server = new SocketIOServer(config);
		server.addEventListener("advert_info", String.class, new DataListener<String>() {
			@Override
			public void onData(SocketIOClient client, String data, AckRequest ackRequest) throws Exception {
				String sa = client.getRemoteAddress().toString();
				String clientIp = sa.substring(1, sa.indexOf(":"));
				//System.out.println( "1:" + data);
				client.sendEvent("advert_info", clientIp);
				if(StringUtils.isNotBlank(data)){
					MessageConstant.clientsAndroidMap.put(data, client);
				}
			}
		});
		
		server.addEventListener("notice_info", String.class, new DataListener<String>() {
			@Override
			public void onData(SocketIOClient client, String data, AckRequest ackRequest) throws Exception {
				String sa = client.getRemoteAddress().toString();
				String clientIp = sa.substring(1, sa.indexOf(":"));
				System.out.println( "2:" + data);
			}
		});
		
		server.addConnectListener(new ConnectListener() {			
			@Override
			public void onConnect(SocketIOClient client) {
				//client.se
				System.out.println(client.getSessionId());
				System.out.println(client.getRemoteAddress());
				String username = client.getHandshakeData().getSingleUrlParam("username");
				if(!StringUtils.isNoneBlank(username)){
					/*return;*/
				}
				String sa = client.getRemoteAddress().toString();
				String clientIp = sa.substring(1, sa.indexOf(":"));
				//String value = SocketSerializable.serialize(client);
				//String uuid = client.getSessionId().toString();
				if(StringUtils.isNotBlank(username)){
					MessageConstant.clientsMap.put(username, client);
				}
				//RedisUtil.hset(socketClientsKey, username, value);
				logger.info("map值:{}",MessageConstant.clientsMap);
			}
		});
		
		server.addDisconnectListener(new DisconnectListener() {			
			@Override
			public void onDisconnect(SocketIOClient client) {
				
				String username = client.getHandshakeData().getSingleUrlParam("username");;
				String sa = client.getRemoteAddress().toString();
				String clientIp = sa.substring(1, sa.indexOf(":"));
				//String uuid = client.getSessionId().toString();
				MessageConstant.clientsMap.remove(username);
				//RedisUtil.hremove(socketClientsKey, username);
				logger.info("DisconnectListener ==> map值:{}",MessageConstant.clientsMap);				
			}
		});
		
		server.start();
		Thread.sleep(Integer.MAX_VALUE);
		server.stop();
	}
	
	public void stopServer() {
		if(server != null){
			server.stop();
			server = null;
		}
	}
	
	public void sendMessageToAllClient(String eventType, String message) {
		Collection<SocketIOClient> clients = server.getAllClients();
		for(SocketIOClient client : clients) {
			client.sendEvent(eventType, message);
		}
	}
	
	public void sendMessageToOneClient(String username, String eventType, String message) {
        try {
            if (username != null && !"".equals(username)) {
            	String clientStr = RedisUtil.hget(socketClientsKey, username);
            	if(clientStr == null){
            		return;
            	}
            	//SocketIOClient client = SocketSerializable.deserialize(clientStr);
                SocketIOClient client = (SocketIOClient) MessageConstant.clientsMap.get(username);
                if (client != null) {
                    client.sendEvent(eventType, message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}	
}