package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.courseTeacherClassroomDO;
import com.hznu.xdd.domain.pojoExam.courseTeacherClassroomDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface courseTeacherClassroomDOMapper {
    long countByExample(courseTeacherClassroomDOExample example);

    int deleteByExample(courseTeacherClassroomDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(courseTeacherClassroomDO record);

    int insertSelective(courseTeacherClassroomDO record);

    List<courseTeacherClassroomDO> selectByExample(courseTeacherClassroomDOExample example);

    courseTeacherClassroomDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") courseTeacherClassroomDO record, @Param("example") courseTeacherClassroomDOExample example);

    int updateByExample(@Param("record") courseTeacherClassroomDO record, @Param("example") courseTeacherClassroomDOExample example);

    int updateByPrimaryKeySelective(courseTeacherClassroomDO record);

    int updateByPrimaryKey(courseTeacherClassroomDO record);
}