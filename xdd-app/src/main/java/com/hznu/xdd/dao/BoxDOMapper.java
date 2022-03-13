package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.BoxDO;
import com.hznu.xdd.domain.pojoExam.BoxDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoxDOMapper {
    long countByExample(BoxDOExample example);

    int deleteByExample(BoxDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoxDO record);

    int insertSelective(BoxDO record);

    List<BoxDO> selectByExample(BoxDOExample example);

    BoxDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoxDO record, @Param("example") BoxDOExample example);

    int updateByExample(@Param("record") BoxDO record, @Param("example") BoxDOExample example);

    int updateByPrimaryKeySelective(BoxDO record);

    int updateByPrimaryKey(BoxDO record);
}