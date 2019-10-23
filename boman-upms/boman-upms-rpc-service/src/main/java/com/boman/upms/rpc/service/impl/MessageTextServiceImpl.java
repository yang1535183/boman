package com.boman.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.boman.common.annotation.BaseService;
import com.boman.common.base.BaseServiceImpl;
import com.boman.common.db.DataSourceEnum;
import com.boman.common.db.DynamicDataSource;
import com.boman.common.util.StringUtil;
import com.boman.upms.dao.mapper.MessageStateMapper;
import com.boman.upms.dao.mapper.MessageTextMapper;
import com.boman.upms.dao.mapper.UpmsUserMapper;
import com.boman.upms.dao.model.MessageText;
import com.boman.upms.dao.model.MessageTextExample;
import com.boman.upms.dao.model.UpmsUser;
import com.boman.upms.dao.model.UpmsUserExample;
import com.boman.upms.rpc.api.MessageTextService;
import com.boman.upms.rpc.factory.AsyncFactory;
import com.boman.upms.rpc.factory.AsyncManager;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* MessageTextService实现
* Created by shuzheng on 2019/3/5.
*/
@Service
@Transactional
@BaseService
public class MessageTextServiceImpl extends BaseServiceImpl<MessageTextMapper, MessageText, MessageTextExample> implements MessageTextService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageTextServiceImpl.class);
    
    /**
     * 用户信息key
     */
    private final static String ZHENG_UPMS_SERVER_SESSION_USER = "zheng-upms-server-session-user";

    @Autowired
    private MessageTextMapper messageTextMapper;
    
    @Autowired
    private UpmsUserMapper upmsUserMapper;
    
    @Autowired
    private MessageStateMapper messageStateMapper;

    /**
     * 查询用户的所以消息
     */
	@Override
	public List<MessageText> selectByMessageForOffsetPage(MessageText messageText, Integer offset, Integer limit) {
		try {
			DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			PageHelper.offsetPage(offset, limit, false);
			List<MessageText> list = null;
			try {
				list = messageTextMapper.selectByMessageForOffsetPage(messageText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} catch (Exception e) {
			LOGGER.debug("数据库操作失败：{}",e);
		}
		DynamicDataSource.clearDataSource();
		return null;
	}

	/**
	 * 新增消息
	 */
	@Override
	public int creatMessage(MessageText messageText) {
		int row = 0;
		if("0".equals(messageText.getType())) {
			row = messageTextMapper.insert(messageText);
			return row;
		}else {
			UpmsUserExample upmsUserExample = new UpmsUserExample();
			upmsUserExample.createCriteria().andUsernameEqualTo(messageText.getRectName());
			UpmsUser user = upmsUserMapper.selectFirstByExample(upmsUserExample);
			if(StringUtil.isNotNull(user.getUserId())){
				return 0;
			}
			row = messageTextMapper.insert(messageText);
			if(row < 1){
				return row;
			}
			
		}
		
		if(!"0".equals(messageText.getType())) {
			UpmsUserExample upmsUserExample = new UpmsUserExample();
			upmsUserExample.createCriteria().andUsernameEqualTo(messageText.getRectName());
			UpmsUser user = upmsUserMapper.selectFirstByExample(upmsUserExample);
		}
		UpmsUserExample upmsUserExample = new UpmsUserExample();
		upmsUserExample.createCriteria().andUsernameEqualTo(messageText.getRectName());
		return 0;
	}

	/**
	 * 统计未读消息总数
	 */
	@Override
	public int getMessageNum(String username) {
		if(username == null){
			return 0;
		}
		int row = messageTextMapper.getMessageNum(username);
		return row;
	}
	
	/**
	 * 查询全部的未读消息数量及标题
	 */
	@Override
	public List<MessageText> selectByLoginName(String username) {
		if(username == null) {
			return null;
		}
		List<MessageText> lists = messageTextMapper.selectByLoginName(username);
		return lists;
	}

	/**
	 * 站内消息主动推送
	 */
	@Override
	public int PushMessage(MessageText messageText) {
		if(!StringUtils.isNotBlank(messageText.getRectName())){
			return 0;
		}
		messageText.setCtime(System.currentTimeMillis());
		int row = messageTextMapper.insert(messageText);
		if(row < 1){
			throw new RuntimeException("messageTextMapper.insert 出错");
		}
		messageStateMapper.insertMessage(messageText.getId() , messageText.getRectName());
		AsyncManager.me().execute(AsyncFactory.recordOper(messageText));
		return row;
	}
	
	/**
	 * 其他子项目推送消息的处理
	 */
	@Override
	public int PushJsonMessage(JSONObject object) {
		String rectName = object.getString("rectName");
		if(!StringUtils.isNotBlank(rectName)){
			return 0;
		}
		MessageText messageText = new MessageText();
		messageText.setCtime(System.currentTimeMillis());
		messageText.setMessage(object.getString("message"));
		messageText.setRectName(rectName);
		messageText.setTittle(object.getString("tittle"));
		int row = messageTextMapper.insert(messageText);
		if(row < 1){
			throw new RuntimeException("messageTextMapper.insert 出错");
		}
		messageStateMapper.insertMessage(messageText.getId() , messageText.getRectName());
		AsyncManager.me().execute(AsyncFactory.recordOper(messageText));
		//MessageConstant.clientsMap.		
		return row;
	}
	
	/**
	 * 统计消息总数
	 */
	@Override
	public long countByMessage(String username) {
		int rows = messageTextMapper.countByExample(username);
		return rows;
	}
	
	/**
	 * 查看日志详细信息
	 */
	@Override
	public MessageText selectById(Integer id) {
		MessageText messageText = messageTextMapper.selectById(id);
		return messageText;
	}
}