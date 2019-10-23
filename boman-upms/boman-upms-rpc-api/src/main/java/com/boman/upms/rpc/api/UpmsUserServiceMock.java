package com.boman.upms.rpc.api;

import com.boman.common.base.BaseServiceMock;
import com.boman.upms.dao.mapper.UpmsUserMapper;
import com.boman.upms.dao.model.UpmsUser;
import com.boman.upms.dao.model.UpmsUserExample;

import java.util.List;

/**
* 降级实现UpmsUserService接口
* Created by shuzheng on 2017/3/20.
*/
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        return null;
    }

    @Override
    public UpmsUser getUserByLoginName(String loginName) {
        return null;
    }

    @Override
    public boolean haveOpenId(String userName) {
        return false;
    }

    @Override
    public void insertOpenId(String openid, String userName) {

    }

    @Override
    public List<UpmsUser> selectByRoleForOffsetPage(String roleId, int offset, int limit) {
        return null;
    }

    @Override
    public Integer selectByRoleCount(String roleId) {
        return 0;
    }
}
