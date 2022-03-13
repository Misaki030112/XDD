package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.OperateLogDO;
import com.hznu.xdd.domain.pojoExam.OperateLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperateLogDOMapper {
    long countByExample(OperateLogDOExample example);

    int deleteByExample(OperateLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperateLogDO record);

    int insertSelective(OperateLogDO record);

    List<OperateLogDO> selectByExample(OperateLogDOExample example);

    OperateLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OperateLogDO record, @Param("example") OperateLogDOExample example);

    int updateByExample(@Param("record") OperateLogDO record, @Param("example") OperateLogDOExample example);

    int updateByPrimaryKeySelective(OperateLogDO record);

    int updateByPrimaryKey(OperateLogDO record);
}