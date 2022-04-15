package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.labelDO;
import com.hznu.xdd.domain.pojoExam.labelDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface labelDOMapper {
    long countByExample(labelDOExample example);

    int deleteByExample(labelDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(labelDO record);

    int insertSelective(labelDO record);

    List<labelDO> selectByExample(labelDOExample example);

    labelDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") labelDO record, @Param("example") labelDOExample example);

    int updateByExample(@Param("record") labelDO record, @Param("example") labelDOExample example);

    int updateByPrimaryKeySelective(labelDO record);

    int updateByPrimaryKey(labelDO record);
}