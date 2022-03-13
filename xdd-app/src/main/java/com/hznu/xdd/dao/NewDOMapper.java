package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.NewDO;
import com.hznu.xdd.domain.pojoExam.NewDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewDOMapper {
    long countByExample(NewDOExample example);

    int deleteByExample(NewDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewDO record);

    int insertSelective(NewDO record);

    List<NewDO> selectByExample(NewDOExample example);

    NewDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewDO record, @Param("example") NewDOExample example);

    int updateByExample(@Param("record") NewDO record, @Param("example") NewDOExample example);

    int updateByPrimaryKeySelective(NewDO record);

    int updateByPrimaryKey(NewDO record);
}