package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.GroupDto;
import com.hznu.xdd.domain.VO.UserPageVO;

import java.util.List;

public interface GroupService {

    public boolean addGroup(GroupDto groupDto);

//    public boolean UpdateGroup(GroupDto groupDto);

    public boolean deleteGroup(Integer id);

    public List<UserPageVO.UserVO> getGroupUser(Integer id);

    public Boolean groupEnd(Integer id);

    public Boolean groupCancel(Integer groupId,Integer userId);

    public GroupDto getGroupInfo(Integer id);




}
