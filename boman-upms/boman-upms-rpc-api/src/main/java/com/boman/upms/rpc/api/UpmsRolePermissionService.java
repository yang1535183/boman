package com.boman.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.boman.common.base.BaseService;
import com.boman.upms.dao.model.UpmsRolePermission;
import com.boman.upms.dao.model.UpmsRolePermissionExample;

/**
* UpmsRolePermissionService接口
*/
public interface UpmsRolePermissionService extends BaseService<UpmsRolePermission, UpmsRolePermissionExample> {

    /**
     * 角色权限
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int rolePermission(JSONArray datas, int id);

}