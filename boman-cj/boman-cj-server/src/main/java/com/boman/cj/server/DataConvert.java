/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DataConvert
 * Author:   Administrator
 * Date:     2019/10/29 11:27
 * Description: 时间转换
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.cj.server;

/**
 * 〈一句话功能简述〉<br> 
 * 〈时间转换〉
 *
 * @author yw
 * @create 2019/10/29
 * @since 1.0.0
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConvert implements  Converter<String, Date> {

    private static final Logger LOGGER= LoggerFactory.getLogger(DataConvert.class);
    public String  datePattern="yyyy-MM-dd HH:mm:ss";

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public Date convert(String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            LOGGER.error("对日期进行格式转换异常！期望的格式：{}",datePattern,e);
        }

        return null;
    }

}
