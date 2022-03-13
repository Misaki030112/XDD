package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.TabDO;
import com.hznu.xdd.domain.pojoExam.TabDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabDOMapper {
    long countByExample(TabDOExample example);

    int deleteByExample(TabDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TabDO record);

    int insertSelective(TabDO record);

    List<TabDO> selectByExample(TabDOExample example);

    TabDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TabDO record, @Param("example") TabDOExample example);

    int updateByExample(@Param("record") TabDO record, @Param("example") TabDOExample example);

    int updateByPrimaryKeySelective(TabDO record);

    int updateByPrimaryKey(TabDO record);
}