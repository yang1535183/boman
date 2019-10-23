package com.boman.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.boman.common.base.BaseServiceMock;
import com.boman.upms.dao.mapper.UpmsRolePermissionMapper;
import com.boman.upms.dao.model.UpmsRolePermission;
import com.boman.upms.dao.model.UpmsRolePermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsRolePermissionService接口
* Created by shuzheng on 2017/3/20.
*/
public class UpmsRolePermissionServiceMock extends BaseServiceMock<UpmsRolePermissionMapper, UpmsRolePermission, UpmsRolePermissionExample> implements UpmsRolePermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsRolePermissionServiceMock.class);

    @Override
    public int rolePermission(JSONArray datas, int id) {
        LOGGER.info("UpmsRolePermissionServiceMock => rolePermission");
        return 0;
    }

}
