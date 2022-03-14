package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.verify_emailDO;
import com.hznu.xdd.pojo.verify_emailDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface verify_emailDOMapper {
    long countByExample(verify_emailDOExample example);

    int deleteByExample(verify_emailDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(verify_emailDO record);

    int insertSelective(verify_emailDO record);

    List<verify_emailDO> selectByExample(verify_emailDOExample example);

    verify_emailDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") verify_emailDO record, @Param("example") verify_emailDOExample example);

    int updateByExample(@Param("record") verify_emailDO record, @Param("example") verify_emailDOExample example);

    int updateByPrimaryKeySelective(verify_emailDO record);

    int updateByPrimaryKey(verify_emailDO record);
}