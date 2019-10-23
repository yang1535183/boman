package com.boman.upms.rpc.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.MultiValueMap;

/**
 * activiti 服务层
 */
public interface ActivitiService {

    /**
     * 获取activity 编辑需要的json
     * @param modelId
     */
    public String getEditorJson(String modelId);

    /**
     * 保存编辑的工作流
     * @param modelId
     * @param jsonObject
     */
    public void saveModel(String modelId, JSONObject jsonObject);

    /**
     * 获取工作流列表
     * @param offset
     * @param limit
     * @param search
     */
    public Object modelList(int offset, int limit, String search);

    /**
     * 新建流程
     * @param name
     * @param key
     * @param descript
     */
    public String goActiviti(String name, String key, String descript, String loginName);

    /**
     * 删除流程
     * @param id
     */
    public void deleteActiviti(String id);

    /**
     * 部署流程
     * @param id
     */
    public void deployActiviti(String id);

    /**
     * 取消部署流程
     * @param id
     */
    public void undeployActiviti(String id);

    /**
     * 获取已部署流程数据
     * @param offset
     * @param limit
     * @param search
     */
    public Object selectApplylist(int offset, int limit, String search);
}
