package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.focusLogDO;
import com.hznu.xdd.domain.pojoExam.focusLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface focusLogDOMapper {
    long countByExample(focusLogDOExample example);

    int deleteByExample(focusLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(focusLogDO record);

    int insertSelective(focusLogDO record);

    List<focusLogDO> selectByExample(focusLogDOExample example);

    focusLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") focusLogDO record, @Param("example") focusLogDOExample example);

    int updateByExample(@Param("record") focusLogDO record, @Param("example") focusLogDOExample example);

    int updateByPrimaryKeySelective(focusLogDO record);

    int updateByPrimaryKey(focusLogDO record);
}