/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: QRcodeController
 * Author:   Administrator
 * Date:     2019/4/17 10:58
 * Description: 生成二维码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.upms.server.controller.manage.QRcode;

import com.boman.common.base.BaseController;
import com.boman.common.util.CookieUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈二维码〉
 *
 * @author jys
 * @create 2019/4/17
 * @since 1.0.0
 */
@Controller
@RequestMapping("/QR/QR")
public class QRcodeController extends BaseController {
    private String separator= File.separator;
    /**
     * 生成二维码图片以及uuid
     * @author
     *
     */
    @ApiOperation("生成二维码图片")
    @GetMapping("/GetQrCodeServlet")
    @ResponseBody
    public Map GetQrCodeServlet(HttpServletRequest request){
        //生成唯一ID
        UUID uuids = UUID.randomUUID();
        String uuid=uuids.toString().replaceAll("-","");
        //二维码内容
        //String content = "http://192.168.101.208:1111/QR/QR/PhoneLoginServlet?uuid="+uuid;
        String content = "http://118.31.23.65:1111/QR/QR/PhoneLoginServlet?uuid="+uuid;
        //生成二维码
        String imgName =  uuid + "_" + (int) (System.currentTimeMillis() / 1000) + ".png";
        //生成当天的文件夹
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String times = dateFormat.format(System.currentTimeMillis());
        //二维码生成的地址
        String imgPath = "/home/file/QRcode/" + times + separator + imgName;
        //String imgPath = "D:\\file/" + times + "\\" + imgName;
        TwoDimensionCode handler = new TwoDimensionCode();
        handler.encoderQRCode(content, imgPath, "png");
        //生成的图片访问地址
        //String qrCodeImg = "http://upms.zhangshuzheng.cn:1111/QR/file/QR/" + times + "/" + imgName;
        String qrCodeImg = "http://118.31.23.65:1111/QR/file/QR/" + times + "/" + imgName;
        //String jsonStr = "{\"uuid\":" + uuid + ",\"qrCodeImg\":\"" + qrCodeImg + "\"}";
        Map map=new HashMap();
        map.put("uuid",uuid);
        map.put("qrCodeImg",qrCodeImg);
        return map;
    }
    /**
     * 用长连接，检查登录状态
     * @author
     *
     */
    @ApiOperation("扫码长连接")
    @GetMapping("/LongConnectionCheckServlet")
    @ResponseBody
    public Map LongConnectionCheckServlet(HttpServletRequest request, HttpServletResponse response){
        String uuid = request.getParameter("uuid");
        String jsonStr = "";
        System.out.println("in");
        System.out.println("uuid:" + uuid);
        long inTime = System.currentTimeMillis();
        Boolean bool = true;
        while (bool) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //检测登录
            UserVo userVo = LoginUserVo.getLoginUserMap().get(uuid);
            System.out.println("userVo:" + userVo);
            if(userVo != null){
                bool = false;
                jsonStr = userVo.getUname();
                LoginUserVo.getLoginUserMap().remove(uuid);
                response.addHeader("Set-Cookie","zheng-upms-server-session-id="+jsonStr+";  Path=/;  HttpOnly");
                System.out.println("login ok : " + jsonStr);
            }else{
                if(System.currentTimeMillis() - inTime > 5000){
                    bool = false;
                    //超时
                    uuid = "false";
                }
            }
        }
        Map map=new HashMap();
        map.put("uuid",uuid);
        map.put("username",jsonStr);
        return map;
    }
    /**
     * 二维码手机端登录
     * @author
     *
     */
    @ApiOperation("手机扫码确认")
    @GetMapping("/PhoneLoginServlet")
    @ResponseBody
    public void PhoneLoginServlet(HttpServletRequest request){
        String server_session_id= CookieUtil.getOneCookie(request);
        String uuid = request.getParameter("uuid");
        String uname = server_session_id;
        //String upwd = request.getParameter("upwd");
        System.out.println("upms_uuid:"+uuid);
        System.out.println("upms_Server_sesionId:"+uname);
        //System.out.println(upwd);
        if(uname!=null&&uname!=""){
            //TODO 验证登录
            boolean bool = true;
            if(bool){
                //将登陆信息存入map
                UserVo userVo = LoginUserVo.getLoginUserMap().get(uuid);
                if(userVo == null){
                    userVo = new UserVo();
                    userVo.setUname(uname);
                    userVo.setUpwd("测试");
                    LoginUserVo.getLoginUserMap().put(uuid, userVo);
                }
            }
        }
    }
}
