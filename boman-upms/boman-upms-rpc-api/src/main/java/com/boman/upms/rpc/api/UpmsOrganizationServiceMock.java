package com.boman.upms.rpc.api;

import com.boman.common.base.BaseServiceMock;
import com.boman.upms.dao.mapper.UpmsOrganizationMapper;
import com.boman.upms.dao.model.UpmsOrganization;
import com.boman.upms.dao.model.UpmsOrganizationExample;

/**
* 降级实现UpmsOrganizationService接口
* Created by shuzheng on 2017/3/20.
*/
public class UpmsOrganizationServiceMock extends BaseServiceMock<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

}
