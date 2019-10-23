package com.boman.common.util.wechat.wechat.util;

import com.boman.common.util.wechat.wechat.pojo.AccessToken;
import com.boman.common.util.wechat.wechat.pojo.OAuthInfo;
import com.boman.common.util.wechat.wechat.pojo.UserInfo;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;


/**
 * 公众平台通用接口工具类
 * 
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	public final static String js_api_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	public final static String jsticket="bxLdikRXVbTPdHSM05e5u8W5L2c5jvDSSiPMQiA3d-wUG1flyddlkZjqWjdplCpWDAKAeXToWhszAat-Yk3DfA";
	public static String testUrl = "https://api.weixin.qq.com/card/qrcode/create?access_token=TOKEN";
	
	
	
	public static String test="{\"card\":{\"card_type\":\"DISCOUNT\",\"discount\":{\"base_info\":{\"title\":\"132元双人火锅套餐\",\"date_info\":{\"end_timestamp\":1472724261,\"begin_timestamp\":1397577600,\"type\":\"DATE_TYPE_FIX_TIME_RANGE\"},\"color\":\"Color010\",\"description\":\"不可用叠加使用\",\"sku\":{\"quantity\":10000},\"notice\":\"请出示你的卡券给服务人员\",\"logo_url\":\"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0\",\"brand_name\":\"测试卡券\",\"code_type\":\"CODE_TYPE_TEXT\"},\"discount\":30}}}";
	public final static String create_ticket="https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";

	//获取ticket的接口地址
	public final static String APITICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		System.out.println("==============进入httpRequest方法===============");
		
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			//URL url = new URL(null,requestUrl,new com.sun.net.ssl.internal.www.protocol.https.Handler()); 
//			URL url = new URL(null,requestUrl,new sun.net.www.protocol.https.Handler()); 
//			URL url = new URL(context, spec)
			System.out.println("==============发送的url"+url+"===============");
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			System.out.println("============== url.openConnection()"+httpUrlConn+"===============");
			
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)){
				httpUrlConn.connect();
			}

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
		
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			System.out.println("============== buffer.toString()"+buffer.toString()+"===============");
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println("============== jsonObject"+jsonObject+"===============");
		} catch (ConnectException ce) {
			//log.error("Weixin server connection timed out.");
			System.out.println("============== Weixin server connection timed out."+ce+"===============");
		} catch (Exception e) {
			//log.error("https request error:{}", e);
			System.out.println("==============https request error:{}"+e+"===============");
		}
		
		System.out.println("==============httpRequest方法结束===============");
		return jsonObject;
	}


	
	
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				//log.error("获取token失败 errcode:{} errmsg:{}", jsonObject
					//	.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";


	
	//网页授权获取openId
	public static String o_auth_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/**
	 * 网页授权获取openId第2步，根据code取得openId
	 * @param appid 公众号的唯一标识
	 * @param secret 公众号的appsecret密钥
	 * @param code code为换取access_token的票据          
	 * @return 
	 */
	public static OAuthInfo getOAuthOpenId(String appid, String secret, String code ) {
		OAuthInfo oAuthInfo = null;
		String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
		System.out.println("==============requestUrl:"+requestUrl+"==============");

		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		System.out.println("==============jsonObject:"+jsonObject+"==============");
		
		// 如果请求成功
		if (null != jsonObject) {
			try {
				oAuthInfo = new OAuthInfo();
				oAuthInfo.setAccessToken(jsonObject.getString("access_token"));
				oAuthInfo.setExpiresIn(jsonObject.getInt("expires_in"));
				oAuthInfo.setRefreshToken(jsonObject.getString("refresh_token"));
				oAuthInfo.setOpenId(jsonObject.getString("openid"));
				oAuthInfo.setScope(jsonObject.getString("scope"));
				System.out.println(oAuthInfo);
			} catch (JSONException e) {
				oAuthInfo = null;
				// 获取token失败
				log.error("网页授权获取openId失败 errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		
		return oAuthInfo;
		
	}

	//获取用户信息接口
	public static String userinfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/**
	 * 网页授权获取用户信息
	 * @param access_token 授权得到的access_token
	 * @param openid  授权获取的openid
	 * @return
	 */
	public static UserInfo getUserInfo(String access_token, String openid ) {
		UserInfo userInfo = null;
		String requestUrl = userinfo_url.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		System.out.println("==============requestUrl:"+requestUrl+"==============");

		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		System.out.println("==============jsonObject:"+jsonObject+"==============");
		
		// 如果请求成功
		if (null != jsonObject) {
			try {
				userInfo = new UserInfo();
				userInfo.setNickname(jsonObject.getString("nickname"));
				userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
			} catch (JSONException e) {
				userInfo = null;
				// 获取token失败
				log.error("网页授权获取openId失败 errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return userInfo;
	}
	//获取用户信息接口
	public static String OAUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/**
	 * 网页授权获取用户信息
	 * @param access_token 授权得到的access_token
	 * @param openid  授权获取的openid
	 * @return
	 */
	public static UserInfo getOAuthUserInfo(String access_token,String openid) {
		UserInfo userInfo = null;
		String requestUrl = OAUTH_USERINFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		log.info("官方授权获取用户信息接口返回的内容"+jsonObject);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				userInfo = new UserInfo();
				userInfo.setNickname(jsonObject.getString("nickname"));
				userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
				userInfo.setOpenid(jsonObject.getString("openid"));
			} catch (JSONException e) {
				userInfo = null;
				log.error("网页授权获取用户信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return userInfo;
	}
	//刷新token
	public static String REFRESH_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	/**
	 * 刷新获取的token
	 * @param appid
	 * @param refresh_token
	 * @return
	 */
	public static OAuthInfo getRefreshTokenInfo(String appid,String refresh_token) {
		OAuthInfo oAuthInfo = null;
		String requestUrl = REFRESH_TOKEN_URL.replace("APPID", appid).replace("REFRESH_TOKEN", refresh_token);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		log.info("getRefreshTokenInfo"+JSONObject.fromObject(jsonObject));
		// 如果请求成功
		if (null != jsonObject) {
			try {
				oAuthInfo = new OAuthInfo();
				oAuthInfo.setAccessToken(jsonObject.getString("access_token"));
				oAuthInfo.setExpiresIn(jsonObject.getInt("expires_in"));
				oAuthInfo.setRefreshToken(jsonObject.getString("refresh_token"));
				oAuthInfo.setOpenId(jsonObject.getString("openid"));
				oAuthInfo.setScope(jsonObject.getString("scope"));
			} catch (JSONException e) {
				oAuthInfo = null;
				log.error("网页授权获取openId失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return oAuthInfo;
	}
}