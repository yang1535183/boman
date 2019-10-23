package com.boman.upms.rpc.api;

import com.boman.common.base.BaseServiceMock;
import com.boman.upms.dao.mapper.UpmsSystemMapper;
import com.boman.upms.dao.model.UpmsSystem;
import com.boman.upms.dao.model.UpmsSystemExample;

/**
* 降级实现UpmsSystemService接口
* Created by shuzheng on 2017/3/20.
*/
public class UpmsSystemServiceMock extends BaseServiceMock<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

    @Override
    public UpmsSystem selectUpmsSystemByName(String name) {
        return null;
    }

}
