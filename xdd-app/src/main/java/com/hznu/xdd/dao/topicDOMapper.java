package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.topicDO;
import com.hznu.xdd.domain.pojoExam.topicDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface topicDOMapper {
    long countByExample(topicDOExample example);

    int deleteByExample(topicDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(topicDO record);

    int insertSelective(topicDO record);

    List<topicDO> selectByExample(topicDOExample example);

    topicDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") topicDO record, @Param("example") topicDOExample example);

    int updateByExample(@Param("record") topicDO record, @Param("example") topicDOExample example);

    int updateByPrimaryKeySelective(topicDO record);

    int updateByPrimaryKey(topicDO record);
}