package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.voteLogDO;
import com.hznu.xdd.domain.pojoExam.voteLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface voteLogDOMapper {
    long countByExample(voteLogDOExample example);

    int deleteByExample(voteLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(voteLogDO record);

    int insertSelective(voteLogDO record);

    List<voteLogDO> selectByExample(voteLogDOExample example);

    voteLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") voteLogDO record, @Param("example") voteLogDOExample example);

    int updateByExample(@Param("record") voteLogDO record, @Param("example") voteLogDOExample example);

    int updateByPrimaryKeySelective(voteLogDO record);

    int updateByPrimaryKey(voteLogDO record);
}