package com.boman.upms.dao.mapper;

import com.boman.upms.dao.model.MessageText;
import com.boman.upms.dao.model.MessageTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageTextMapper {
    long countByExample(MessageTextExample example);

    int deleteByExample(MessageTextExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageText record);

    int insertSelective(MessageText record);

    List<MessageText> selectByExample(MessageTextExample example);

    MessageText selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageText record, @Param("example") MessageTextExample example);

    int updateByExample(@Param("record") MessageText record, @Param("example") MessageTextExample example);

    int updateByPrimaryKeySelective(MessageText record);

    int updateByPrimaryKey(MessageText record);

    /**
     * 查询用户的所以消息
     * @param messageText
     */
	public List<MessageText> selectByMessageForOffsetPage(MessageText messageText);

	public int getMessageNum(String username);

	/**
	 * 查询全部的未读消息数量及标题
	 * @param username
	 */
	public List<MessageText> selectByLoginName(String username);

	/**
	 * 统计消息总数
	 * @param username
	 */
	public int countByExample(String username);
	
	/**
	 * 查看日志详细信息
	 * @param id
	 */
	public MessageText selectById(Integer id);
}