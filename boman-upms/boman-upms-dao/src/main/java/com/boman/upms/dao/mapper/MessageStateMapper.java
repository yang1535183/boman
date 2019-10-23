package com.boman.upms.dao.mapper;

import com.boman.upms.dao.model.MessageState;
import com.boman.upms.dao.model.MessageStateExample;
import com.boman.upms.dao.model.MessageText;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageStateMapper {
    long countByExample(MessageStateExample example);

    int deleteByExample(MessageStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageState record);

    int insertSelective(MessageState record);

    List<MessageState> selectByExample(MessageStateExample example);

    MessageState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageState record, @Param("example") MessageStateExample example);

    int updateByExample(@Param("record") MessageState record, @Param("example") MessageStateExample example);

    int updateByPrimaryKeySelective(MessageState record);

    int updateByPrimaryKey(MessageState record);

	public void insertMessage(@Param("messageId") int messageId, @Param("rectName") String rectName);
}