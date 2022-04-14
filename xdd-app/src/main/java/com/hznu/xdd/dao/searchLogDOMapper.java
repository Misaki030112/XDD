package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.searchLogDO;
import com.hznu.xdd.domain.pojoExam.searchLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface searchLogDOMapper {
    long countByExample(searchLogDOExample example);

    int deleteByExample(searchLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(searchLogDO record);

    int insertSelective(searchLogDO record);

    List<searchLogDO> selectByExample(searchLogDOExample example);

    searchLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") searchLogDO record, @Param("example") searchLogDOExample example);

    int updateByExample(@Param("record") searchLogDO record, @Param("example") searchLogDOExample example);

    int updateByPrimaryKeySelective(searchLogDO record);

    int updateByPrimaryKey(searchLogDO record);
}