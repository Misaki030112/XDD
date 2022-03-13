package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.VoteLogDO;
import com.hznu.xdd.domain.pojoExam.VoteLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteLogDOMapper {
    long countByExample(VoteLogDOExample example);

    int deleteByExample(VoteLogDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoteLogDO record);

    int insertSelective(VoteLogDO record);

    List<VoteLogDO> selectByExample(VoteLogDOExample example);

    VoteLogDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoteLogDO record, @Param("example") VoteLogDOExample example);

    int updateByExample(@Param("record") VoteLogDO record, @Param("example") VoteLogDOExample example);

    int updateByPrimaryKeySelective(VoteLogDO record);

    int updateByPrimaryKey(VoteLogDO record);
}