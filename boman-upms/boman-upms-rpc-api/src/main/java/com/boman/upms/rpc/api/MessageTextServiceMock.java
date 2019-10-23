package com.boman.upms.rpc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.boman.common.base.BaseServiceMock;
import com.boman.upms.dao.mapper.MessageTextMapper;
import com.boman.upms.dao.model.MessageText;
import com.boman.upms.dao.model.MessageTextExample;

/**
* 降级实现MessageTextService接口
*/
public class MessageTextServiceMock extends BaseServiceMock<MessageTextMapper, MessageText, MessageTextExample> implements MessageTextService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageTextServiceMock.class);
	
	@Override
	public List<MessageText> selectByMessageForOffsetPage(MessageText messageText, Integer offset, Integer limit) {
		LOGGER.debug("MessageTextServiceMock => selectByMessageForOffsetPage");
		return null;
	}

	@Override
	public int creatMessage(MessageText messageText) {
		LOGGER.debug("MessageTextServiceMock => creatMessage");
		return 0;
	}

	@Override
	public int getMessageNum(String username) {
		LOGGER.debug("MessageTextServiceMock => getMessageNum");
		return 0;
	}

	@Override
	public List<MessageText> selectByLoginName(String username) {
		LOGGER.debug("MessageTextServiceMock => selectByLoginName");
		return null;
	}

	@Override
	public int PushMessage(MessageText messageText) {
		LOGGER.debug("MessageTextServiceMock => PushMessage");
		return 0;
	}

	@Override
	public int PushJsonMessage(JSONObject object) {
		LOGGER.debug("MessageTextServiceMock => PushJsonMessage");
		return 0;
	}

	@Override
	public long countByMessage(String loginName) {
		LOGGER.debug("MessageTextServiceMock => countByMessage");
		return 0;
	}

	@Override
	public MessageText selectById(Integer id) {
		LOGGER.debug("MessageTextServiceMock => selectById");
		return null;
	}
}