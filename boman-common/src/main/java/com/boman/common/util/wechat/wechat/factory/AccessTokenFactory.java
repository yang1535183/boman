package com.boman.common.util.wechat.wechat.factory;


import com.boman.common.util.wechat.wechat.pojo.AccessToken;
import com.boman.common.util.wechat.wechat.util.Constants;
import com.boman.common.util.wechat.wechat.util.WeixinUtil;

public class AccessTokenFactory {
	private AccessToken accessToken;
	public  AccessToken getAccessToken(){
		accessToken = WeixinUtil.getAccessToken(Constants.appId, Constants.appSecret);
		return accessToken;
	}

}
