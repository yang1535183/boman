/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DownLoadUtil
 * Author:   Administrator
 * Date:     2019/6/3 11:48
 * Description: 下载
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈下载〉
 *
 * @author jys
 * @create 2019/6/3
 * @since 1.0.0
 */
public class DownLoadUtil {
    /**
     * 单个文件下载
     * @param path 路径
     * @param response
     * @return
     */
    public  static Object download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }catch (FileNotFoundException e){
            return "404";
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
