/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LoginUserVo
 * Author:   Administrator
 * Date:     2019/4/17 11:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.upms.server.controller.manage.QRcode;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author jys
 * @create 2019/4/17
 * @since 1.0.0
 */
import java.util.HashMap;

public class LoginUserVo {
    private static HashMap<String, UserVo> loginUserMap = new HashMap<String, UserVo>();
    private static LoginUserVo loginUserVo;
    public static LoginUserVo getVo(){
        if(loginUserVo == null){
            loginUserVo = new LoginUserVo();
        }
        return loginUserVo;
    }
    public static HashMap<String, UserVo> getLoginUserMap() {
        return loginUserMap;
    }
}
