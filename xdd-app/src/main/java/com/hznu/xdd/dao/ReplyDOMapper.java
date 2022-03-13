package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.ReplyDO;
import com.hznu.xdd.domain.pojoExam.ReplyDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReplyDOMapper {
    long countByExample(ReplyDOExample example);

    int deleteByExample(ReplyDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReplyDO record);

    int insertSelective(ReplyDO record);

    List<ReplyDO> selectByExample(ReplyDOExample example);

    ReplyDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReplyDO record, @Param("example") ReplyDOExample example);

    int updateByExample(@Param("record") ReplyDO record, @Param("example") ReplyDOExample example);

    int updateByPrimaryKeySelective(ReplyDO record);

    int updateByPrimaryKey(ReplyDO record);
}