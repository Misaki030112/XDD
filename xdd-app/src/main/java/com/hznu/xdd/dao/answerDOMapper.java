package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.answerDO;
import com.hznu.xdd.domain.pojoExam.answerDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface answerDOMapper {
    long countByExample(answerDOExample example);

    int deleteByExample(answerDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(answerDO record);

    int insertSelective(answerDO record);

    List<answerDO> selectByExample(answerDOExample example);

    answerDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") answerDO record, @Param("example") answerDOExample example);

    int updateByExample(@Param("record") answerDO record, @Param("example") answerDOExample example);

    int updateByPrimaryKeySelective(answerDO record);

    int updateByPrimaryKey(answerDO record);
}