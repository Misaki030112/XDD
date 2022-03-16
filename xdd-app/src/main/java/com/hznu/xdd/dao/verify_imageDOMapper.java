package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.verify_imageDO;
import com.hznu.xdd.domain.pojoExam.verify_imageDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface verify_imageDOMapper {
    long countByExample(verify_imageDOExample example);

    int deleteByExample(verify_imageDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(verify_imageDO record);

    int insertSelective(verify_imageDO record);

    List<verify_imageDO> selectByExample(verify_imageDOExample example);

    verify_imageDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") verify_imageDO record, @Param("example") verify_imageDOExample example);

    int updateByExample(@Param("record") verify_imageDO record, @Param("example") verify_imageDOExample example);

    int updateByPrimaryKeySelective(verify_imageDO record);

    int updateByPrimaryKey(verify_imageDO record);
}