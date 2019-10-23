/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SocketUtil
 * Author:   Administrator
 * Date:     2019/4/12 10:06
 * Description: 消息系统
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.common.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消息系统〉
 *
 * @author jys
 * @create 2019/4/12
 * @since 1.0.0
 */
public class SocketUtil {
    private static class SingletonInstance {
        private static final JSONObject INSTANCE = new JSONObject();
    }

    public static JSONObject getInstance(String rectName,String msg,String title) {
        JSONObject json=SingletonInstance.INSTANCE;
        json.put("rectName",rectName);
        json.put("message", msg);
        json.put("tittle", title);
        return json;
    }
}
