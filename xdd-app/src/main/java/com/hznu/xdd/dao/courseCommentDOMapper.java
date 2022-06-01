package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.courseCommentDO;
import com.hznu.xdd.domain.pojoExam.courseCommentDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface courseCommentDOMapper {
    long countByExample(courseCommentDOExample example);

    int deleteByExample(courseCommentDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(courseCommentDO record);

    int insertSelective(courseCommentDO record);

    List<courseCommentDO> selectByExample(courseCommentDOExample example);

    courseCommentDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") courseCommentDO record, @Param("example") courseCommentDOExample example);

    int updateByExample(@Param("record") courseCommentDO record, @Param("example") courseCommentDOExample example);

    int updateByPrimaryKeySelective(courseCommentDO record);

    int updateByPrimaryKey(courseCommentDO record);
}