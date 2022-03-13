package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.CollectLogDO;
import com.hznu.xdd.domain.pojoExam.CollectLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectLogDOMapper {
    long countByExample(CollectLogDOExample example);

    int deleteByExample(CollectLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CollectLogDO record);

    int insertSelective(CollectLogDO record);

    List<CollectLogDO> selectByExample(CollectLogDOExample example);

    CollectLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CollectLogDO record, @Param("example") CollectLogDOExample example);

    int updateByExample(@Param("record") CollectLogDO record, @Param("example") CollectLogDOExample example);

    int updateByPrimaryKeySelective(CollectLogDO record);

    int updateByPrimaryKey(CollectLogDO record);
}