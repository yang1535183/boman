package com.boman.common.util.act;

import com.google.common.collect.Lists;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * activiti中使用得到的工具方法
 * @author Created by yawn on 2018-01-10 16:32
 */
public class ActivitiUtil {

    /**
     * 将历史参数列表设置到实体中去
     * @param entity 实体
     * @param varInstanceList 历史参数列表
     */
    public static <T> void setVars(T entity, List<HistoricVariableInstance> varInstanceList) {
        Class<?> tClass = entity.getClass();
        try {
            for (HistoricVariableInstance varInstance : varInstanceList) {
                Field field = tClass.getDeclaredField(varInstance.getVariableName());
                if (field == null) {
                    continue;
                }
                field.setAccessible(true);
                field.set(entity, varInstance.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param processEngine 工作流引擎
     * @param account 账号
     * @return 真实姓名
     */
    public static String getRealName(ProcessEngine processEngine,String account){
        User user =  processEngine.getIdentityService().createUserQuery().userId(account)
                .singleResult();
        return user.getFirstName();
    }

    /**
     * oa过滤其他工作流数据 只保留 发文 请假  收文
     * @return
     */
    public static List<String> oaFilterProcDef(){
        List<String> list= Lists.newArrayList();
        list.add(ProcDefKey.OA_DISPATCH.getKey());
        list.add(ProcDefKey.OA_LEAVE.getKey());
        list.add(ProcDefKey.OA_INCOMING.getKey());
        return list;
    }

    /**
     * 招商过滤其他工作流数据 只保留 项目 外出 协办
     * @return
     */
    public static List<String> zsFilterProcDef(){
        List<String> list= Lists.newArrayList();
        list.add(ProcDefKey.ZS_PROJECT.getKey());
        list.add(ProcDefKey.ZS_HELP.getKey());
        list.add(ProcDefKey.ZS_OUT.getKey());
        return list;
    }


}
