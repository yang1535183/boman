package com.boman.upms.rpc.api;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

/**
 * ActivitiService 降级实现
 */
public class ActivitiServiceMock implements ActivitiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiServiceMock.class);

    @Override
    public String getEditorJson(String modelId) {
        LOGGER.info("ActivitiServiceMock ==> getEditorJson");
        return null;
    }

    @Override
    public void saveModel(String modelId, JSONObject jsonObject) {
        LOGGER.info("ActivitiServiceMock ==> saveModel");
    }

    @Override
    public Object modelList(int offset, int limit, String search) {
        LOGGER.info("ActivitiServiceMock ==> modelList");
        return null;
    }

    @Override
    public String goActiviti(String name, String key, String descript, String loginName) {
        LOGGER.info("ActivitiServiceMock ==> goActiviti");
        return null;
    }

    @Override
    public void deleteActiviti(String id) {
        LOGGER.info("ActivitiServiceMock ==> goActiviti");
        throw new RuntimeException();
    }

    @Override
    public void deployActiviti(String id) {
        LOGGER.info("ActivitiServiceMock ==> deployActiviti");
        throw new RuntimeException();
    }

    @Override
    public void undeployActiviti(String id) {
        LOGGER.info("ActivitiServiceMock ==> undeployActiviti");
        throw new RuntimeException();
    }

    @Override
    public Object selectApplylist(int offset, int limit, String search) {
        LOGGER.info("ActivitiServiceMock ==> selectApplylist");
        return null;
    }
}
