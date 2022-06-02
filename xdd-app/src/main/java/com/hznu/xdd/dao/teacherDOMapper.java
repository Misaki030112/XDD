package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.teacherDO;
import com.hznu.xdd.domain.pojoExam.teacherDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface teacherDOMapper {
    long countByExample(teacherDOExample example);

    int deleteByExample(teacherDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(teacherDO record);

    int insertSelective(teacherDO record);

    List<teacherDO> selectByExample(teacherDOExample example);

    teacherDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") teacherDO record, @Param("example") teacherDOExample example);

    int updateByExample(@Param("record") teacherDO record, @Param("example") teacherDOExample example);

    int updateByPrimaryKeySelective(teacherDO record);

    int updateByPrimaryKey(teacherDO record);
    
    
    int selectTeacherIdByTeacherNameAndSchoolNameAndCollegeName(String teacher,String school,String college);
    
}