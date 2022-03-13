package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.UserReportDO;
import com.hznu.xdd.domain.pojoExam.UserReportDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserReportDOMapper {
    long countByExample(UserReportDOExample example);

    int deleteByExample(UserReportDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserReportDO record);

    int insertSelective(UserReportDO record);

    List<UserReportDO> selectByExample(UserReportDOExample example);

    UserReportDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserReportDO record, @Param("example") UserReportDOExample example);

    int updateByExample(@Param("record") UserReportDO record, @Param("example") UserReportDOExample example);

    int updateByPrimaryKeySelective(UserReportDO record);

    int updateByPrimaryKey(UserReportDO record);
}