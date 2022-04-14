package com.hznu.xdd.service.impl;


import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.dao.groupDOMapper;
import com.hznu.xdd.dao.groupJoinLogDOMapper;
import com.hznu.xdd.domain.VO.UserVO;
import com.hznu.xdd.domain.pojoExam.groupJoinLogDOExample;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.pojo.groupDO;
import com.hznu.xdd.pojo.groupJoinLogDO;
import com.hznu.xdd.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    groupJoinLogDOMapper groupJoinLogDOMapper;

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    groupDOMapper groupDOMapper;

    @Override
    public List<UserVO> getGroupUser(Integer id) {
        groupJoinLogDOExample groupJoinLogDOExample = new groupJoinLogDOExample();
        com.hznu.xdd.domain.pojoExam.groupJoinLogDOExample.Criteria criteria = groupJoinLogDOExample.createCriteria();
        criteria.andGroup_idEqualTo(id).andIs_cancelEqualTo(false).andIs_deleteEqualTo(false);
        List<groupJoinLogDO> groupJoinLogDOS = groupJoinLogDOMapper.selectByExample(groupJoinLogDOExample);
        ArrayList<UserVO> userVOS = new ArrayList<UserVO>();
        groupJoinLogDOS.forEach((ele)->{
            UserDO userDO = userDOMapper.selectByPrimaryKey(ele.getUser_id());
            UserVO userVO = new UserVO();
            userVO.setGender(userDO.getGender())
                    .setNickname(userDO.getNickname())
                    .setAvatar(userDO.getAvatar())
                    .setId(userDO.getId())
                    .setJoin_time(ele.getCreate_time());
            userVOS.add(userVO);
        });
        return userVOS;
    }

    @Override
    public Boolean groupEnd(Integer id) {
        groupDO groupDO = groupDOMapper.selectByPrimaryKey(id);
        groupDO.setIs_open("false");
        int i = groupDOMapper.updateByPrimaryKey(groupDO);
        return i == 1;
    }
}
