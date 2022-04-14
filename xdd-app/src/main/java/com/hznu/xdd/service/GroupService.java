package com.hznu.xdd.service;

import com.hznu.xdd.domain.VO.UserVO;

import java.util.List;

public interface GroupService {

    public List<UserVO> getGroupUser(Integer id);

    public Boolean groupEnd(Integer id);
}
