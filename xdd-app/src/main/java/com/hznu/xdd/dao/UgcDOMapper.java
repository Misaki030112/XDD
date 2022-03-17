package com.hznu.xdd.dao;

import com.hznu.xdd.domain.pojoExam.UgcDOExample;
import com.hznu.xdd.pojo.UgcDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UgcDOMapper {
    long countByExample(UgcDOExample example);

    int deleteByExample(UgcDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UgcDO record);

    int insertSelective(UgcDO record);

    List<UgcDO> selectByExample(UgcDOExample example);

    UgcDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UgcDO record, @Param("example") UgcDOExample example);

    int updateByExample(@Param("record") UgcDO record, @Param("example") UgcDOExample example);

    int updateByPrimaryKeySelective(UgcDO record);

    int updateByPrimaryKey(UgcDO record);
}