package com.boman.upms.rpc.api;

import com.boman.common.base.BaseService;
import com.boman.upms.dao.model.UpmsUser;
import com.boman.upms.dao.model.UpmsUserExample;

import java.util.List;
import java.util.Map;

/**
* UpmsUserService接口
* Created by shuzheng on 2017/3/20.
*/
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);

    UpmsUser getUserByLoginName(String loginName);

    /**
     * 数据库是否有openid
     * @param userName
     * @return true 有  false 没有
     */
    boolean haveOpenId(String userName);

    /**
     * 绑定openid
     * @param openid
     * @param userName 账号
     */
    void insertOpenId(String openid,String userName);

    /**
     * 根据roleId来查询对应的用户
     * @param roleId
     * @param offset
     * @param limit
     */
    public List<UpmsUser> selectByRoleForOffsetPage(String roleId, int offset, int limit);

    public Integer selectByRoleCount(String roleId);

    public List<Map<Object,Object>> userOrg(UpmsUser upmsUser);
}