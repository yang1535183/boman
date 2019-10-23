package com.boman.upms.rpc.api;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boman.common.base.BaseService;
import com.boman.upms.dao.model.MessageText;
import com.boman.upms.dao.model.MessageTextExample;


/**
* MessageTextService接口
* Created by shuzheng on 2019/3/5.
*/
public interface MessageTextService extends BaseService<MessageText, MessageTextExample> {
	
	/**
	 * 查询用户的所以消息
	 */
	public List<MessageText> selectByMessageForOffsetPage(MessageText messageText, Integer offset, Integer limit);

	/**
	 * 新增消息
	 * @param messageText
	 */
	public int creatMessage(MessageText messageText);
	
	/**
	 * 统计未读消息总数
	 */
	public int getMessageNum(String username);
	
	/**
	 * 查询全部的未读消息数量及标题
	 * @param loginName
	 */
	public List<MessageText> selectByLoginName(String username);
	
	/**
	 * 站内消息主动推送
	 */
	public int PushMessage(MessageText messageText);
	
	/**
	 * 站内Json消息主动推送
	 */
	public int PushJsonMessage(JSONObject object);
	
	/**
	 * 统计消息总数
	 * @param loginName
	 */
	public long countByMessage(String loginName);
	
	/**
	 * 查看日志的详情
	 * @param id
	 */
	public MessageText selectById(Integer id);
}