package com.boman.upms.rpc.service.impl;

import com.boman.common.annotation.BaseService;
import com.boman.common.base.BaseServiceImpl;
import com.boman.upms.dao.mapper.UpmsRoleMapper;
import com.boman.upms.dao.model.UpmsRole;
import com.boman.upms.dao.model.UpmsRoleExample;
import com.boman.upms.rpc.api.UpmsRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsRoleService实现
*/
@Service
@Transactional
@BaseService
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsRoleServiceImpl.class);

    @Autowired
    UpmsRoleMapper upmsRoleMapper;

}