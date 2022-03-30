package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.courseDO;
import com.hznu.xdd.domain.pojoExam.courseDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface courseDOMapper {
    long countByExample(courseDOExample example);

    int deleteByExample(courseDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(courseDO record);

    int insertSelective(courseDO record);

    List<courseDO> selectByExample(courseDOExample example);

    courseDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") courseDO record, @Param("example") courseDOExample example);

    int updateByExample(@Param("record") courseDO record, @Param("example") courseDOExample example);

    int updateByPrimaryKeySelective(courseDO record);

    int updateByPrimaryKey(courseDO record);
}