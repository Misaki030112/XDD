package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.UgcJoinDO;
import com.hznu.xdd.domain.pojoExam.UgcJoinDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UgcJoinDOMapper {
    long countByExample(UgcJoinDOExample example);

    int deleteByExample(UgcJoinDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UgcJoinDO record);

    int insertSelective(UgcJoinDO record);

    List<UgcJoinDO> selectByExample(UgcJoinDOExample example);

    UgcJoinDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UgcJoinDO record, @Param("example") UgcJoinDOExample example);

    int updateByExample(@Param("record") UgcJoinDO record, @Param("example") UgcJoinDOExample example);

    int updateByPrimaryKeySelective(UgcJoinDO record);

    int updateByPrimaryKey(UgcJoinDO record);
}