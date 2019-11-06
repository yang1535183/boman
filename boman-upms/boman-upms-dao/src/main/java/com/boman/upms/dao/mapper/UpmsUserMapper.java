package com.boman.upms.dao.mapper;

import com.boman.upms.dao.model.UpmsUser;
import com.boman.upms.dao.model.UpmsUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UpmsUserMapper {
    long countByExample(UpmsUserExample example);

    UpmsUser getUserByLoginName(String loginName);

    int deleteByExample(UpmsUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UpmsUser record);

    int insertSelective(UpmsUser record);

    List<UpmsUser> selectByExample(UpmsUserExample example);

    UpmsUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UpmsUser record, @Param("example") UpmsUserExample example);

    int updateByExample(@Param("record") UpmsUser record, @Param("example") UpmsUserExample example);

    int updateByPrimaryKeySelective(UpmsUser record);

    int updateByPrimaryKey(UpmsUser record);
    
    /**
     * 查询第一条记录
     * @param upmsUserExample
     * @return
     */
	 UpmsUser selectFirstByExample(UpmsUserExample upmsUserExample);

    /**
     * 数据库是否有openid
     * @param userName
     * @return true 有  false 没有
     */
    int haveOpenId(String userName);

    /**
     * 绑定openid
     * @param openid
     * @param userName 账号
     */
    void insertOpenId(@Param("openid") String openid,@Param("userName") String userName);

    /**
     * 根据roleId 查询用户
     * @param roleId
     */
    public List<UpmsUser> selectByRole(String roleId);

    /**
     * 根据roleId 查询用户总数
     * @param roleId
     */
    public Integer selectByRoleCount(String roleId);

    public List<Map<Object,Object>> userOrg(UpmsUser upmsUser);
}