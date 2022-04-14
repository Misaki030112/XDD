package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.groupJoinLogDO;
import com.hznu.xdd.domain.pojoExam.groupJoinLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface groupJoinLogDOMapper {
    long countByExample(groupJoinLogDOExample example);

    int deleteByExample(groupJoinLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(groupJoinLogDO record);

    int insertSelective(groupJoinLogDO record);

    List<groupJoinLogDO> selectByExample(groupJoinLogDOExample example);

    groupJoinLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") groupJoinLogDO record, @Param("example") groupJoinLogDOExample example);

    int updateByExample(@Param("record") groupJoinLogDO record, @Param("example") groupJoinLogDOExample example);

    int updateByPrimaryKeySelective(groupJoinLogDO record);

    int updateByPrimaryKey(groupJoinLogDO record);
}