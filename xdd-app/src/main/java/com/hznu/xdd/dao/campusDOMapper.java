package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.campusDO;
import com.hznu.xdd.domain.pojoExam.campusDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface campusDOMapper {
    long countByExample(campusDOExample example);

    int deleteByExample(campusDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(campusDO record);

    int insertSelective(campusDO record);

    List<campusDO> selectByExample(campusDOExample example);

    campusDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") campusDO record, @Param("example") campusDOExample example);

    int updateByExample(@Param("record") campusDO record, @Param("example") campusDOExample example);

    int updateByPrimaryKeySelective(campusDO record);

    int updateByPrimaryKey(campusDO record);
    
    
    int selectByCampusNameAndSchoolName(String campus,String school);
}