package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.SlideDO;
import com.hznu.xdd.domain.pojoExam.SlideDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SlideDOMapper {
    long countByExample(SlideDOExample example);

    int deleteByExample(SlideDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SlideDO record);

    int insertSelective(SlideDO record);

    List<SlideDO> selectByExample(SlideDOExample example);

    SlideDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SlideDO record, @Param("example") SlideDOExample example);

    int updateByExample(@Param("record") SlideDO record, @Param("example") SlideDOExample example);

    int updateByPrimaryKeySelective(SlideDO record);

    int updateByPrimaryKey(SlideDO record);
}