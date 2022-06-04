package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.collegeDO;
import com.hznu.xdd.domain.pojoExam.collegeDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface collegeDOMapper {
    long countByExample(collegeDOExample example);

    int deleteByExample(collegeDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(collegeDO record);

    int insertSelective(collegeDO record);

    List<collegeDO> selectByExample(collegeDOExample example);

    collegeDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") collegeDO record, @Param("example") collegeDOExample example);

    int updateByExample(@Param("record") collegeDO record, @Param("example") collegeDOExample example);

    int updateByPrimaryKeySelective(collegeDO record);

    int updateByPrimaryKey(collegeDO record);


    Integer selectCollegeIdAndSchoolName(@Param("college") String college ,@Param("school") String school);
}