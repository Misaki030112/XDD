package com.hznu.xdd.dao;

import com.hznu.xdd.domain.pojoExam.ugcCommentDOExample;
import com.hznu.xdd.pojo.ugcCommentDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ugcCommentDOMapper {
    long countByExample(ugcCommentDOExample example);

    int deleteByExample(ugcCommentDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ugcCommentDO record);

    int insertSelective(ugcCommentDO record);

    List<ugcCommentDO> selectByExample(ugcCommentDOExample example);

    ugcCommentDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ugcCommentDO record, @Param("example") ugcCommentDOExample example);

    int updateByExample(@Param("record") ugcCommentDO record, @Param("example") ugcCommentDOExample example);

    int updateByPrimaryKeySelective(ugcCommentDO record);

    int updateByPrimaryKey(ugcCommentDO record);
}