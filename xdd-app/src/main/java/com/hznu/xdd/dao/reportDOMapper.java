package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.reportDO;
import com.hznu.xdd.domain.pojoExam.reportDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface reportDOMapper {
    long countByExample(reportDOExample example);

    int deleteByExample(reportDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(reportDO record);

    int insertSelective(reportDO record);

    List<reportDO> selectByExample(reportDOExample example);

    reportDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") reportDO record, @Param("example") reportDOExample example);

    int updateByExample(@Param("record") reportDO record, @Param("example") reportDOExample example);

    int updateByPrimaryKeySelective(reportDO record);

    int updateByPrimaryKey(reportDO record);
}