package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.GroupDto;
import com.hznu.xdd.domain.VO.UserVO;

import java.util.List;

public interface GroupService {

    public boolean addGroup(GroupDto groupDto);

//    public boolean UpdateGroup(GroupDto groupDto);

    public boolean deleteGroup(Integer id);

    public List<UserVO> getGroupUser(Integer id);

    public Boolean groupEnd(Integer id);


}
