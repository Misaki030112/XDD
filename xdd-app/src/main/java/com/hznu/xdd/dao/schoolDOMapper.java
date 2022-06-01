package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.schoolDO;
import com.hznu.xdd.domain.pojoExam.schoolDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface schoolDOMapper {
    long countByExample(schoolDOExample example);

    int deleteByExample(schoolDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(schoolDO record);

    int insertSelective(schoolDO record);

    List<schoolDO> selectByExample(schoolDOExample example);

    schoolDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") schoolDO record, @Param("example") schoolDOExample example);

    int updateByExample(@Param("record") schoolDO record, @Param("example") schoolDOExample example);

    int updateByPrimaryKeySelective(schoolDO record);

    int updateByPrimaryKey(schoolDO record);
}