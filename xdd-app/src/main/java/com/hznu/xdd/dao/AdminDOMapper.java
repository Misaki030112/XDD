package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.AdminDO;
import com.hznu.xdd.domain.pojoExam.AdminDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminDOMapper {
    long countByExample(AdminDOExample example);

    int deleteByExample(AdminDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminDO record);

    int insertSelective(AdminDO record);

    List<AdminDO> selectByExample(AdminDOExample example);

    AdminDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminDO record, @Param("example") AdminDOExample example);

    int updateByExample(@Param("record") AdminDO record, @Param("example") AdminDOExample example);

    int updateByPrimaryKeySelective(AdminDO record);

    int updateByPrimaryKey(AdminDO record);
}