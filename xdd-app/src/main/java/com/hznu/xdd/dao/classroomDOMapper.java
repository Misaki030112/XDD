package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.classroomDO;
import com.hznu.xdd.domain.pojoExam.classroomDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface classroomDOMapper {
    long countByExample(classroomDOExample example);

    int deleteByExample(classroomDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(classroomDO record);

    int insertSelective(classroomDO record);

    List<classroomDO> selectByExample(classroomDOExample example);

    classroomDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") classroomDO record, @Param("example") classroomDOExample example);

    int updateByExample(@Param("record") classroomDO record, @Param("example") classroomDOExample example);

    int updateByPrimaryKeySelective(classroomDO record);

    int updateByPrimaryKey(classroomDO record);
}