package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.ChatMessageDO;
import com.hznu.xdd.domain.pojoExam.ChatMessageDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatMessageDOMapper {
    long countByExample(ChatMessageDOExample example);

    int deleteByExample(ChatMessageDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChatMessageDO record);

    int insertSelective(ChatMessageDO record);

    List<ChatMessageDO> selectByExample(ChatMessageDOExample example);

    ChatMessageDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChatMessageDO record, @Param("example") ChatMessageDOExample example);

    int updateByExample(@Param("record") ChatMessageDO record, @Param("example") ChatMessageDOExample example);

    int updateByPrimaryKeySelective(ChatMessageDO record);

    int updateByPrimaryKey(ChatMessageDO record);
}