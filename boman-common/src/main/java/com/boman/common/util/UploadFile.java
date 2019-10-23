/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UploadFile
 * Author:   Administrator
 * Date:     2019/5/16 11:47
 * Description: 上传文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈上传文件〉
 *
 * @author jys
 * @create 2019/5/16
 * @since 1.0.0
 */
public class UploadFile {
    private static  final Logger logger= LoggerFactory.getLogger(UploadFile.class);
    /**项目上传*/
    public static List<String> upload(CommonsMultipartFile[] files, String userName){
        List<String> list=new ArrayList<>();
        if(files.length!=0&&userName!=null){
            //生成当天的文件夹
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String times = dateFormat.format(System.currentTimeMillis());
            try{
                for (CommonsMultipartFile commonsMultipartFile:files){
                   /* long  endTime=System.currentTimeMillis();*/
                    String name=commonsMultipartFile.getOriginalFilename();
                    name=name.replaceAll(",","");
                   /* name=name.trim();
                    int one=name.lastIndexOf(".");
                    int length=name.length();
                    String appName=name.substring(one,length);*/
                    /*String path="D:/file/"+userName+"/"+times+"/"+endTime+appName;*/
                    String path=userName+"/"+times+"/"+name;
                    File file=new File("/home/file/project/"+path);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    commonsMultipartFile.transferTo(file);
                    list.add(path);
                }
            }catch (IOException e){
                logger.error("上传错误："+e.getMessage());
                list.clear();
            }
        }
        return list;
    }
    /*信息动态banner*/
    public static String newsUpload(CommonsMultipartFile multipartFile, String userName){
            //生成当天的文件夹
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String times = dateFormat.format(System.currentTimeMillis());
            long  endTime=System.currentTimeMillis();
            String name = multipartFile.getOriginalFilename();
            name = name.trim();
            int one = name.lastIndexOf(".");
            int length = name.length();
            String appName = name.substring(one,length);
            String path = userName+"/"+times+"/"+endTime+appName;
            try{
                    File file = new File("/home/file/news/banner/"+path);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                multipartFile.transferTo(file);
            }catch (IOException e){
                logger.error("上传错误："+e.getMessage());
            }
        return path;
    }
}
