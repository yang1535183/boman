package com.boman.upms.rpc.service.impl;

import com.boman.common.annotation.BaseService;
import com.boman.common.base.BaseServiceImpl;
import com.boman.common.db.DataSourceEnum;
import com.boman.common.db.DynamicDataSource;
import com.boman.upms.dao.mapper.UpmsUserMapper;
import com.boman.upms.dao.model.UpmsUser;
import com.boman.upms.dao.model.UpmsUserExample;
import com.boman.upms.rpc.api.UpmsUserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* UpmsUserService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(upmsUser.getUsername());
        long count = upmsUserMapper.countByExample(upmsUserExample);
        if (count > 0) {
            return null;
        }
        upmsUserMapper.insert(upmsUser);
        return upmsUser;
    }

    @Override
    public UpmsUser getUserByLoginName(String loginName) {
        return upmsUserMapper.getUserByLoginName(loginName);
    }

    @Override
    public boolean haveOpenId(String userName) {
        boolean flag=true;
        int count=upmsUserMapper.haveOpenId(userName);
        if(count==0){
            flag=false;
        }
        return flag;
    }

    @Override
    public void insertOpenId(String openid, String userName) {
        upmsUserMapper.insertOpenId(openid,userName);
    }

    /**
     * 根据roleId 查询用户
     * @param roleId
     * @param offset
     * @param limit
     */
    @Override
    public List<UpmsUser> selectByRoleForOffsetPage(String roleId, int offset, int limit) {
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        offset = offset < 0 ? 0 : offset;
        PageHelper.offsetPage(offset, limit, false);
        List<UpmsUser> list = upmsUserMapper.selectByRole(roleId);
        return list;
    }

    /**
     * 根据roleId 查询用户总数
     * @param roleId
     */
    @Override
    public Integer selectByRoleCount(String roleId) {
        return upmsUserMapper.selectByRoleCount(roleId);
    }

    @Override
    public List<Map<Object,Object>> userOrg(UpmsUser upmsUser) {
        return upmsUserMapper.userOrg(upmsUser);
    }
}