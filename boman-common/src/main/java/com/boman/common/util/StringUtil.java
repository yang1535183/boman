package com.boman.common.util;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 工具类
 */
public class StringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     * @return
     */
    public static String lineToHump(String str){
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        if(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String humpToLine(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s){
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 判断会话是否为活动会话
     * @param username
     */
    public static boolean isActivity(String username) {
        Jedis jedis = RedisUtil.getJedis();
        String id = jedis.hget("zheng-upms-server-session-user", username);
        if(id != null) {
            String session = RedisUtil.get("zheng-upms-shiro-session-id" + "_" + id);
            if(session != null){
                jedis.close();
                return true;
            }
        }
        jedis.close();
        return false;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNotNull(object);
    }

    /**
     * 把带逗号的字符串转集合
     * @param string
     * @return
     */
    public static List<String> splitStr(String string){
        List<String> leaderList = new ArrayList<>();
        String [] userStr=string.split(",");
        for(int i=0;i<userStr.length;i++){
            if(userStr[i]!=""){
                //
                leaderList.add(userStr[i]);
            }
        }
        return  leaderList;
    }
}
