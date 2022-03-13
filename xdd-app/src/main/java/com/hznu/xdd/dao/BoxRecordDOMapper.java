package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.BoxRecordDO;
import com.hznu.xdd.domain.pojoExam.BoxRecordDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoxRecordDOMapper {
    long countByExample(BoxRecordDOExample example);

    int deleteByExample(BoxRecordDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoxRecordDO record);

    int insertSelective(BoxRecordDO record);

    List<BoxRecordDO> selectByExample(BoxRecordDOExample example);

    BoxRecordDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoxRecordDO record, @Param("example") BoxRecordDOExample example);

    int updateByExample(@Param("record") BoxRecordDO record, @Param("example") BoxRecordDOExample example);

    int updateByPrimaryKeySelective(BoxRecordDO record);

    int updateByPrimaryKey(BoxRecordDO record);
}