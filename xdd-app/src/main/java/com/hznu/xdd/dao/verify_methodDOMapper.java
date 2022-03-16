package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.verify_methodDO;
import com.hznu.xdd.domain.pojoExam.verify_methodDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface verify_methodDOMapper {
    long countByExample(verify_methodDOExample example);

    int deleteByExample(verify_methodDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(verify_methodDO record);

    int insertSelective(verify_methodDO record);

    List<verify_methodDO> selectByExample(verify_methodDOExample example);

    verify_methodDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") verify_methodDO record, @Param("example") verify_methodDOExample example);

    int updateByExample(@Param("record") verify_methodDO record, @Param("example") verify_methodDOExample example);

    int updateByPrimaryKeySelective(verify_methodDO record);

    int updateByPrimaryKey(verify_methodDO record);
}