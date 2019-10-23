package com.boman.upms.rpc.api;

import com.boman.common.base.BaseServiceMock;
import com.boman.upms.dao.mapper.UpmsRoleMapper;
import com.boman.upms.dao.model.UpmsRole;
import com.boman.upms.dao.model.UpmsRoleExample;

/**
* 降级实现UpmsRoleService接口
*/
public class UpmsRoleServiceMock extends BaseServiceMock<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {

}
