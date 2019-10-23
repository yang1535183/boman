package com.boman.upms.rpc.service.impl;

import com.boman.common.annotation.BaseService;
import com.boman.common.base.BaseServiceImpl;
import com.boman.upms.dao.mapper.MessageStateMapper;
import com.boman.upms.dao.model.MessageState;
import com.boman.upms.dao.model.MessageStateExample;
import com.boman.upms.rpc.api.MessageStateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* MessageStateService实现
* Created by shuzheng on 2019/3/5.
*/
@Service
@Transactional
@BaseService
public class MessageStateServiceImpl extends BaseServiceImpl<MessageStateMapper, MessageState, MessageStateExample> implements MessageStateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageStateServiceImpl.class);

    @Autowired
    MessageStateMapper messageStateMapper;

}