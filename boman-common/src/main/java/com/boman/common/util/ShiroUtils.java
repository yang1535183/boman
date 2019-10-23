package com.boman.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ShiroUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtils.class);

    /**
     * 获取登录的账号
     * @return
     */
    public static String getUsername(HttpServletRequest request){
        String username=null;
        try {
            String sessionId=CookieUtil.getOneCookie(request);
            username=RedisUtil.get(sessionId);
        }catch (Exception e){
            LOGGER.error("获取当前账号有误！！",e);
        }
        return username;
    }

    /**
     * 获取用户的登录名称
     */
    public static String getLoginName()	{
        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
    }


}
