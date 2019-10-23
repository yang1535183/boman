/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ProcDefKey
 * Author:   Administrator
 * Date:     2019/5/17 14:16
 * Description: ProcDefKey
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.common.util.act;

/**
 * 〈一句话功能简述〉<br> 
 * 〈启动流程的key〉
 *
 * @author jys
 * @create 2019/5/17
 * @since 1.0.0
 */
public enum  ProcDefKey {
//招商
    /**项目*/
    ZS_PROJECT("project_1")
    /**协办*/
    ,ZS_HELP("projectHelp_1")
    /**外出申请*/
    ,ZS_OUT("project_out_1")

//oa
    /**发文*/
    ,OA_DISPATCH("receiptAndDispatch")
    /**请假*/
    ,OA_LEAVE("myProcess_1")
    /**收文*/
    ,OA_INCOMING("incomingBpmn");

    private String key;
    ProcDefKey(String key) {
        this.key=key;
    }
    public String getKey() {
        return key;
    }
}
