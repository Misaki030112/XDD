package com.hznu.xdd.dao;

import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.domain.pojoExam.UserDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface UserDOMapper {
    long countByExample(UserDOExample example);

    int deleteByExample(UserDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    List<UserDO> selectByExample(UserDOExample example);

    UserDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDO record, @Param("example") UserDOExample example);

    int updateByExample(@Param("record") UserDO record, @Param("example") UserDOExample example);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    @Insert("insert into user(open_id_xiaododo_mini,union_id,create_time,update_time) values(#{user.open_id_xiaododo_mini}," +
            "#{user.union_id},#{user.create_time},#{user.update_time})")
    @Options(keyProperty="user.id",useGeneratedKeys=true)
    int addNewUser(@Param("user")UserDO user);
}