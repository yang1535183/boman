/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MyTaskListener
 * Author:   Administrator
 * Date:     2018/11/27 16:33
 * Description: 监听器类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.common.util.act;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 〈一句话功能简述〉<br> 
 * 〈监听器类〉
 *
 * @author Administrator
 * @create 2018/11/27
 * @since 1.0.0
 */
public class MyTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {//DelegateTask 代理任务对象
        //  Auto-generated method stub
        delegateTask.setAssignee("liu001");//指定办理人
//		delegateTask.addCandidateUser("liu002");
    }
}