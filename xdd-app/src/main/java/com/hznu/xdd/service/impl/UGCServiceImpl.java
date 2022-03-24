package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.hznu.xdd.dao.UgcDOMapper;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.dao.ugcCommentDOMapper;
import com.hznu.xdd.dao.voteLogDOMapper;
import com.hznu.xdd.dao.collectLogDOMapper;
import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.Dto.attachmentDto;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.domain.VO.UserVO;
import com.hznu.xdd.domain.pojoExam.UgcDOExample;
import com.hznu.xdd.domain.pojoExam.collectLogDOExample;
import com.hznu.xdd.domain.pojoExam.voteLogDOExample;
import com.hznu.xdd.pojo.*;
import com.hznu.xdd.service.UGCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UGCServiceImpl implements UGCService {

    @Autowired
    UgcDOMapper ugcDOMapper;

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    ugcCommentDOMapper ugcCommentDOMapper;

    @Autowired
    voteLogDOMapper voteLogDOMapper;

    @Autowired
    collectLogDOMapper collectLogDOMapper;

    /**
     * 获取所有评论
     * @return
     */
    @Override
    public List<UgcDO> listAllUGC() {
        UgcDOExample ugcDOExample = new UgcDOExample();
        UgcDOExample.Criteria criteria = ugcDOExample.createCriteria();
        criteria.andIdIsNotNull();
        return ugcDOMapper.selectByExample(ugcDOExample);
    }

    /**
     * 获取指定id的评论
     * @param id
     * @return
     */
    @Override
    public UgcDO findById(Integer id) {
        return ugcDOMapper.selectByPrimaryKey(id);
    }

    /**
     * 新建一个评论
     * @param UGCDto
     * @return
     */
    @Override
    public Integer createUGC(UGCDto UGCDto) {
        UgcDO ugcDO = new UgcDO();
        System.out.println(UGCDto.getAttachment());
        UGCDto.getAttachment().forEach(attachment -> {
            if (attachment.getAttachment_type().equals("image")){
                if (ugcDO.getImages() != null){
                    ugcDO.setImages(ugcDO.getImages() + ',' + attachment.getAttachment_url());
                }
                ugcDO.setImages(attachment.getAttachment_url());
            }else {
                if (ugcDO.getVideo() != null){
                    ugcDO.setVideo(ugcDO.getVideo() + ',' + attachment.getAttachment_url());
                }
                ugcDO.setVideo(attachment.getAttachment_url());
            }
        });
        if (UGCDto.getTitle() != null){
            ugcDO.setTitle(UGCDto.getTitle());
        }
        if(UGCDto.isAnonymous()){
            ugcDO.setAnonymous(UGCDto.isAnonymous());
        }
        if (UGCDto.getLocation() != null){
            ugcDO.setLocation(JSON.toJSONString(UGCDto.getLocation()));
        }
        ugcDO.setLabel(UGCDto.getLabel());
        ugcDO.setContent(UGCDto.getContent());
        ugcDO.setUser_id(UGCDto.getUserId());
        ugcDO.setIs_delete(false);

        return ugcDOMapper.insertSelective(ugcDO);
    }


    /**
     * 删除一个评论
     * @param id
     * @return
     */
    @Override
    public Integer deleteUGC(Integer id) {
        UgcDO ugcDO = ugcDOMapper.selectByPrimaryKey(id);
        ugcDO.setIs_delete(false);
        return ugcDOMapper.updateByPrimaryKeySelective(ugcDO);
    }

    /**
     * 更新一条评论
     * @param UGCDto
     * @return
     */
    @Override
    public Integer updateUGC(UGCDto UGCDto) {
        UgcDO ugcDO = ugcDOMapper.selectByPrimaryKey(UGCDto.getId());
        UGCDto.getAttachment().forEach(attachment -> {
            if (attachment.getAttachment_type().equals("image")){
                if (ugcDO.getImages() != null){
                    ugcDO.setImages(ugcDO.getImages() + ',' + attachment.getAttachment_url());
                }
                ugcDO.setImages(attachment.getAttachment_url());
            }else {
                if (ugcDO.getVideo() != null){
                    ugcDO.setVideo(ugcDO.getVideo() + ',' + attachment.getAttachment_url());
                }
                ugcDO.setVideo(attachment.getAttachment_url());
            }
        });
        if (UGCDto.getTitle() != null){
            ugcDO.setTitle(UGCDto.getTitle());
        }
        if(UGCDto.isAnonymous()){
            ugcDO.setAnonymous(UGCDto.isAnonymous());
        }
        if (UGCDto.getLabel() != null){
            ugcDO.setLabel(UGCDto.getLabel());
        }
        if (UGCDto.getContent() != null){
            ugcDO.setContent(UGCDto.getContent());
        }
        if (UGCDto.getLocation() != null){
            ugcDO.setLocation(JSON.toJSONString(UGCDto.getLocation()));
        }
        return ugcDOMapper.updateByPrimaryKeySelective(ugcDO);
    }

    /**
     * 根据fun获取用户发布或是点赞的评论
     * @param user_id
     * @param key
     * @param label
     * @param topic
     * @param order_by
     * @param fun
     * @return
     */
    @Override
    public List<UGCVO> listPublishUGCById(Integer user_id, String key, String label, String topic, String order_by,Integer fun) {
        UgcDOExample ugcDOExample = new UgcDOExample();
        UgcDOExample.Criteria criteria1 = ugcDOExample.createCriteria();
        UgcDOExample.Criteria criteria2 = ugcDOExample.createCriteria();
        if (fun == 1){
            criteria1.andUser_idEqualTo(user_id);
            criteria2.andUser_idEqualTo(user_id);
        }else if (fun == 2){
            voteLogDOExample voteLogDOExample = new voteLogDOExample();
            com.hznu.xdd.domain.pojoExam.voteLogDOExample.Criteria criteria = voteLogDOExample.createCriteria();
            criteria.andIs_deleteEqualTo(false);
            criteria.andUser_idEqualTo(user_id);
            List<voteLogDO> voteLogDOS = voteLogDOMapper.selectByExample(voteLogDOExample);
            List<Integer> integers = new ArrayList<Integer>();
            voteLogDOS.forEach((txt)->{
                integers.add(txt.getVote_to_id());
            });
            if (integers.size() == 0){
                return null;
            }
            criteria1.andIdIn(integers);
            criteria2.andIdIn(integers);
        } else if (fun == 3){
            criteria1.andIdEqualTo(user_id);
            criteria2.andIdEqualTo(user_id);
        }else if (fun == 4){
            collectLogDOExample collectLogDOExample = new collectLogDOExample();
            com.hznu.xdd.domain.pojoExam.collectLogDOExample.Criteria criteria = collectLogDOExample.createCriteria();
            criteria.andIs_deleteEqualTo(false);
            criteria.andUser_idEqualTo(user_id);
            List<collectLogDO> collectLogDOS = collectLogDOMapper.selectByExample(collectLogDOExample);
            List<Integer> integers = new ArrayList<Integer>();
            collectLogDOS.forEach((txt)->{
                integers.add(txt.getCollect_to_id());
            });
            if (integers.size() == 0){
                return null;
            }
            criteria1.andIdIn(integers);
            criteria2.andIdIn(integers);
        }
        criteria1.andIs_deleteEqualTo(false);
        criteria2.andIs_deleteEqualTo(false);
        if (key != null){
            criteria1.andContentLike("%" + key + "%");
            criteria2.andTitleLike("%" + key + "%");
        }
        if (label != null){
            criteria1.andLabelEqualTo(label);
            criteria2.andLabelEqualTo(label);
        }
        if (topic != null){
            criteria1.andTopicEqualTo(topic);
            criteria2.andTopicEqualTo(topic);
        }
        ugcDOExample.or(criteria2);
        if (order_by != null){
            ugcDOExample.setOrderByClause(order_by);
        }
        List<UGCVO> ugcvos = new ArrayList<UGCVO>();
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(ugcDOExample);
        ugcDOS.forEach((txt)->{
            UGCVO ugcvo = new UGCVO();
            List<attachmentDto> attachmentDtos = new ArrayList<attachmentDto>();
            if (!txt.getImages().equals("")){
                String[] split = txt.getImages().split(",");
                for (String s : split) {
                    attachmentDto attachmentDto = new attachmentDto();
                    attachmentDto.setAttachment_type("image").setAttachment_url(s);
                    attachmentDtos.add(attachmentDto);
                }
            }
            if (!txt.getVideo().equals("")){
                String[] split = txt.getVideo().split(",");
                for (String s : split) {
                    attachmentDto attachmentDto = new attachmentDto();
                    attachmentDto.setAttachment_type("video").setAttachment_url(s);
                    attachmentDtos.add(attachmentDto);
                }
            }
            ugcvo.setId(txt.getId());
            ugcvo.setCreate_time(txt.getCreate_time());
            ugcvo.setUpdate_time(txt.getUpdate_time());
            if(!txt.getLabel().equals(""))ugcvo.setLabel(Arrays.asList(txt.getLabel().split(",")));
            ugcvo.setTopic(txt.getTopic());
            ugcvo.setContent(txt.getContent());
            ugcvo.setAttachmentList(attachmentDtos);
            ugcvo.set_collect(true);
            ugcvo.set_vote(true);
            UserDO userDO = userDOMapper.selectByPrimaryKey(txt.getUser_id());
            UserVO userVO = new UserVO();
            userVO.setId(user_id);
            userVO.setAvatar(userDO.getAvatar());
            userVO.setNickname(userDO.getNickname());
            userVO.setGender(userDO.getGender());
            userVO.setRole(userDO.getRole());
            ugcvo.setUser(userVO);
            ugcvos.add(ugcvo);
        });
        return ugcvos;
    }

    /**
     * 评论UGC
     * @param content
     * @param parent_id
     * @param to_type
     * @param to_id
     * @param user_id
     * @return
     */
    @Override
    public Integer addComment(String content, Integer parent_id, String to_type, Integer to_id,Integer user_id) {
        ugcCommentDO ugcCommentDO = new ugcCommentDO();
        ugcCommentDO.setContent(content);
        ugcCommentDO.setParent_id(parent_id);
        ugcCommentDO.setIs_delete(false);
        ugcCommentDO.setUser_id(user_id);
        return ugcCommentDOMapper.insert(ugcCommentDO);
    }

    /**
     * 点赞UGC
     * @param to_id
     * @param status
     * @param user_id
     * @return
     */
    @Override
    public Integer voteUGC(Integer to_id, boolean status,Integer user_id) {
        int count;
        voteLogDOExample voteLogDOExample = new voteLogDOExample();
        com.hznu.xdd.domain.pojoExam.voteLogDOExample.Criteria criteria = voteLogDOExample.createCriteria();
        criteria.andUser_idEqualTo(user_id);
        criteria.andVote_to_idEqualTo(to_id);
        if (voteLogDOMapper.selectByExample(voteLogDOExample) == null){
            voteLogDO voteLogDO = new voteLogDO();
            voteLogDO.setVote_to_id(to_id);
            voteLogDO.setVote_type("ugc");
            voteLogDO.setIs_delete(status);
            voteLogDO.setUser_id(user_id);
            count = voteLogDOMapper.insert(voteLogDO);
        }else {
            List<voteLogDO> voteLogDOS = voteLogDOMapper.selectByExample(voteLogDOExample);
            voteLogDO voteLogDO = voteLogDOS.get(0);
            voteLogDO.setIs_delete(status);
            count = voteLogDOMapper.updateByPrimaryKey(voteLogDO);
        }
        return count;
    }

    /**
     * 收藏UGC
     * @param to_id
     * @param status
     * @param user_id
     * @return
     */
    @Override
    public Integer collectUGC(Integer to_id, boolean status, Integer user_id) {
        int count;
        collectLogDOExample collectLogDOExample = new collectLogDOExample();
        com.hznu.xdd.domain.pojoExam.collectLogDOExample.Criteria criteria = collectLogDOExample.createCriteria();
        criteria.andUser_idEqualTo(user_id);
        criteria.andCollect_to_idEqualTo(to_id);
        if (collectLogDOMapper.selectByExample(collectLogDOExample) == null){
            collectLogDO collectLogDO = new collectLogDO();
            collectLogDO.setCollect_to_id(to_id);
            collectLogDO.setCollect_type("ugc");
            collectLogDO.setIs_delete(status);
            collectLogDO.setUser_id(user_id);
            count = collectLogDOMapper.insert(collectLogDO);
        }else {
            List<collectLogDO> collectLogDOs = collectLogDOMapper.selectByExample(collectLogDOExample);
            collectLogDO collectLogDO = collectLogDOs.get(0);
            collectLogDO.setIs_delete(status);
            count = collectLogDOMapper.updateByPrimaryKey(collectLogDO);
        }
        return count;
    }
}
