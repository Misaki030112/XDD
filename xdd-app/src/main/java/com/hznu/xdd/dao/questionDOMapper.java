package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.questionDO;
import com.hznu.xdd.domain.pojoExam.questionDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface questionDOMapper {
    long countByExample(questionDOExample example);

    int deleteByExample(questionDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(questionDO record);

    int insertSelective(questionDO record);

    List<questionDO> selectByExample(questionDOExample example);

    questionDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") questionDO record, @Param("example") questionDOExample example);

    int updateByExample(@Param("record") questionDO record, @Param("example") questionDOExample example);

    int updateByPrimaryKeySelective(questionDO record);

    int updateByPrimaryKey(questionDO record);
}