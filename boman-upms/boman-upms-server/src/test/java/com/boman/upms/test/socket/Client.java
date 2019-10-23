package com.boman.upms.test.socket;


import io.socket.client.IO;
import io.socket.client.Socket;

import java.util.Arrays;

public class Client {

    public static void main(String[] args){
        String url = "http://192.168.101.55:40000?username=1480f7da-aca4-4a82-bbd2-c436cc39e85d";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            //失败重试次数
            options.reconnectionAttempts = 10;
            //失败重连的时间间隔
            options.reconnectionDelay = 1000;
            //连接超时时间(ms)
            options.timeout = 500;
            final Socket socket = IO.socket(url, options);
            //监听自定义msg事件
            socket.on("advert_info", objects -> System.out.println("client: 收到msg->" + Arrays.toString(objects)));
            //监听自定义订阅事件
            socket.on("advert_info", objects -> System.out.println("client: " + "订阅成功，收到反馈->" + Arrays.toString(objects)));
            socket.on(Socket.EVENT_CONNECT, objects -> {
                //socket.emit("advert_info", "我是訂閲對象");
                System.out.println("client: " + "连接成功");
            });
            socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
            socket.on(Socket.EVENT_CONNECT_TIMEOUT, objects -> System.out.println("client: " + "连接超时"));
            socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
            socket.connect();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
