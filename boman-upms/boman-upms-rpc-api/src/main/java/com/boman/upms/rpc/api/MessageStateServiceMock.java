package com.boman.upms.rpc.api;

import com.boman.common.base.BaseServiceMock;
import com.boman.upms.dao.mapper.MessageStateMapper;
import com.boman.upms.dao.model.MessageState;
import com.boman.upms.dao.model.MessageStateExample;

/**
* 降级实现MessageStateService接口
* Created by shuzheng on 2019/3/5.
*/
public class MessageStateServiceMock extends BaseServiceMock<MessageStateMapper, MessageState, MessageStateExample> implements MessageStateService {

}
