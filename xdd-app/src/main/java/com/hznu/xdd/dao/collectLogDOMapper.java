package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.collectLogDO;
import com.hznu.xdd.domain.pojoExam.collectLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface collectLogDOMapper {
    long countByExample(collectLogDOExample example);

    int deleteByExample(collectLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(collectLogDO record);

    int insertSelective(collectLogDO record);

    List<collectLogDO> selectByExample(collectLogDOExample example);

    collectLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") collectLogDO record, @Param("example") collectLogDOExample example);

    int updateByExample(@Param("record") collectLogDO record, @Param("example") collectLogDOExample example);

    int updateByPrimaryKeySelective(collectLogDO record);

    int updateByPrimaryKey(collectLogDO record);
}