package com.hznu.xdd.service.impl;


import com.alibaba.fastjson.JSON;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.dao.groupDOMapper;
import com.hznu.xdd.dao.groupJoinLogDOMapper;
import com.hznu.xdd.domain.Dto.GroupDto;
import com.hznu.xdd.domain.VO.UserVO;
import com.hznu.xdd.domain.pojoExam.groupJoinLogDOExample;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.pojo.groupDO;
import com.hznu.xdd.pojo.groupJoinLogDO;
import com.hznu.xdd.service.GroupService;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    groupJoinLogDOMapper groupJoinLogDOMapper;

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    groupDOMapper groupDOMapper;
    @Autowired
    UserInfoUtil userInfoUtil;

    @Override
    public boolean addGroup(GroupDto groupDto) {
        groupDO groupDO = new groupDO();
        groupDO.setCreate_time(new Date())
                .setUpdate_time(new Date())
                .setIs_delete(false)
                .setUser_id(userInfoUtil.getUserId())
                .setTitle(groupDto.getTitle())
                .setContent(groupDto.getContent())
                .setExposure(0)
                .setIs_online(false)
                .setCollect(0)
                .setComment(0)
                .setParams(groupDto.getParams())
                .setLabel(groupDto.getLabel())
                .setTopic(groupDto.getTopic())
                .setIs_open(true);
        GroupDto.Location location = groupDto.getLocation();
        groupDO.setLocation( JSON.toJSONString(location));
        List<GroupDto.Attachment> attachment = groupDto.getAttachment();
        List<String> images = new ArrayList<>();
        List<String> videos = new ArrayList<>();
        attachment.forEach(a->{
            if(Objects.equals(a.getAttachment_type(), "image")){
                images.add(a.getAttachment_url());
            }
            if(Objects.equals(a.getAttachment_type(), "video")){
                videos.add(a.getAttachment_url());
            }
        });
        groupDO.setVideo(JSON.toJSONString(videos));
        groupDO.setImages(JSON.toJSONString(images));

        int i = groupDOMapper.insert(groupDO);
        return i>0;
    }

    @Override
    public boolean deleteGroup(Integer id) {
        groupDO groupDO = groupDOMapper.selectByPrimaryKey(id);
        groupDO.setIs_delete(true);
        int i = groupDOMapper.updateByPrimaryKey(groupDO);
        return i>0;
    }

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
        groupDO.setIs_open(false);
        int i = groupDOMapper.updateByPrimaryKey(groupDO);
        return i == 1;
    }
}
