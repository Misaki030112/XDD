package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.groupDO;
import com.hznu.xdd.domain.pojoExam.groupDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface groupDOMapper {
    long countByExample(groupDOExample example);

    int deleteByExample(groupDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(groupDO record);

    int insertSelective(groupDO record);

    List<groupDO> selectByExample(groupDOExample example);

    groupDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") groupDO record, @Param("example") groupDOExample example);

    int updateByExample(@Param("record") groupDO record, @Param("example") groupDOExample example);

    int updateByPrimaryKeySelective(groupDO record);

    int updateByPrimaryKey(groupDO record);
}