/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: JsonUtil
 * Author:   Administrator
 * Date:     2019/5/17 17:28
 * Description: 数据格式
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.common.util;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据格式〉
 *
 * @author jys
 * @create 2019/5/17
 * @since 1.0.0
 */
public class JsonUtil {
    /**
     * 成功
     */
    public static final byte SUCCESS=1;
    /**
     * 失败
     */
    public static final byte FAIL=0;


    /**
     * 分页 数据格式
     * @param pageNum 当前页
     * @param total 总条数
     * @param obj 数据
     * @return
     */
    public static Map pageTable(Integer pageNum, Long total, Object obj){
        Map<String,Object> map= Maps.newHashMap();
        map.put("pageNum",pageNum);
        map.put("total",total);
        map.put("rows",obj);
        return map;
    }


    public static Map result(byte code, String message){
        Map<String,Object> map= Maps.newHashMap();
        map.put("code",code);
        map.put("msg",message);
        return map;
    }
}
