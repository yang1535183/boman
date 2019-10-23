package com.boman.upms.rpc.api;

import com.boman.common.base.BaseServiceMock;
import com.boman.upms.dao.mapper.UpmsLogMapper;
import com.boman.upms.dao.model.UpmsLog;
import com.boman.upms.dao.model.UpmsLogExample;

/**
* 降级实现UpmsLogService接口
*/
public class UpmsLogServiceMock extends BaseServiceMock<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

}
