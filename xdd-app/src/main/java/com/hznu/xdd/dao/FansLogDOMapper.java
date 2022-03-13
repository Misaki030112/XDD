package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.FansLogDO;
import com.hznu.xdd.domain.pojoExam.FansLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FansLogDOMapper {
    long countByExample(FansLogDOExample example);

    int deleteByExample(FansLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FansLogDO record);

    int insertSelective(FansLogDO record);

    List<FansLogDO> selectByExample(FansLogDOExample example);

    FansLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FansLogDO record, @Param("example") FansLogDOExample example);

    int updateByExample(@Param("record") FansLogDO record, @Param("example") FansLogDOExample example);

    int updateByPrimaryKeySelective(FansLogDO record);

    int updateByPrimaryKey(FansLogDO record);
}