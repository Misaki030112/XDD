package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.NoticeDO;
import com.hznu.xdd.domain.pojoExam.NoticeDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeDOMapper {
    long countByExample(NoticeDOExample example);

    int deleteByExample(NoticeDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoticeDO record);

    int insertSelective(NoticeDO record);

    List<NoticeDO> selectByExample(NoticeDOExample example);

    NoticeDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoticeDO record, @Param("example") NoticeDOExample example);

    int updateByExample(@Param("record") NoticeDO record, @Param("example") NoticeDOExample example);

    int updateByPrimaryKeySelective(NoticeDO record);

    int updateByPrimaryKey(NoticeDO record);
}