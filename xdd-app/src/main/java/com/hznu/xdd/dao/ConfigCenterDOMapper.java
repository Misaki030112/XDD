package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.ConfigCenterDO;
import com.hznu.xdd.domain.pojoExam.ConfigCenterDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigCenterDOMapper {
    long countByExample(ConfigCenterDOExample example);

    int deleteByExample(ConfigCenterDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigCenterDO record);

    int insertSelective(ConfigCenterDO record);

    List<ConfigCenterDO> selectByExampleWithBLOBs(ConfigCenterDOExample example);

    List<ConfigCenterDO> selectByExample(ConfigCenterDOExample example);

    ConfigCenterDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigCenterDO record, @Param("example") ConfigCenterDOExample example);

    int updateByExampleWithBLOBs(@Param("record") ConfigCenterDO record, @Param("example") ConfigCenterDOExample example);

    int updateByExample(@Param("record") ConfigCenterDO record, @Param("example") ConfigCenterDOExample example);

    int updateByPrimaryKeySelective(ConfigCenterDO record);

    int updateByPrimaryKeyWithBLOBs(ConfigCenterDO record);

    int updateByPrimaryKey(ConfigCenterDO record);
}