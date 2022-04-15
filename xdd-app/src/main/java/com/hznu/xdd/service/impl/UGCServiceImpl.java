package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.hznu.xdd.dao.UgcDOMapper;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.dao.ugcCommentDOMapper;
import com.hznu.xdd.dao.voteLogDOMapper;
import com.hznu.xdd.dao.collectLogDOMapper;
import com.hznu.xdd.dao.topicDOMapper;
import com.hznu.xdd.dao.labelDOMapper;
import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.Dto.attachmentDto;
import com.hznu.xdd.domain.VO.CommentVO;
import com.hznu.xdd.domain.VO.ListVO;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.domain.VO.UserVO;
import com.hznu.xdd.domain.pojoExam.*;
import com.hznu.xdd.pojo.*;
import com.hznu.xdd.service.UGCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    topicDOMapper topicDOMapper;

    @Autowired
    labelDOMapper labelDOMapper;

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
        if (UGCDto.getAttachment() != null){
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
        }
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
        ugcDO.setUser_id(UGCDto.getUser_id());
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
        ugcDO.setIs_delete(true);
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
        if (UGCDto.getAttachment() != null){
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
        }
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
     * 获取UGC
     * @param user_id
     * @param key
     * @param label
     * @param topic
     * @param order_by
     * @param page
     * @param offset
     * @param fun
     * @return
     */
    @Override
    public ListVO listPublishUGCById(Integer user_id, String key, String label, String topic, String order_by, Integer page, Integer offset , Integer fun) {
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
        }else if (fun == 3 && user_id != null){
            criteria1.andIdEqualTo(user_id);
            criteria2.andIdEqualTo(user_id);
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
        if (order_by != null){
            ugcDOExample.setOrderByClause(order_by);
        }
        ugcDOExample.or(criteria2);
        int size = ugcDOMapper.selectByExample(ugcDOExample).size();
        if (page != null && offset != null){
            ugcDOExample.page(page,offset);
        }
        List<UGCVO> ugcvos = new ArrayList<UGCVO>();
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(ugcDOExample);
        BatchUGC(ugcDOS, ugcvos);
        ListVO listVO = new ListVO().setList(ugcvos).setTotal(size);
        return listVO;
    }

    /**
     * 获取热门帖子
     * @param page
     * @param offset
     * @return
     */
    @Override
    public ListVO getHotUGC(Integer page, Integer offset) {
        UgcDOExample ugcDOExample = new UgcDOExample();
        int size = ugcDOMapper.selectByExample(ugcDOExample).size();
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(ugcDOExample);
        List<UGCVO> ugcvos = new ArrayList<UGCVO>();
        BatchUGC(ugcDOS, ugcvos);
        Collections.sort(ugcvos, new Comparator<UGCVO>() {
            @Override
            public int compare(UGCVO o1, UGCVO o2) {
                return o2.getScore().compareTo(o1.getScore());
            }
        });
        return new ListVO().setList(ugcvos).setTotal(size);
    }

    private void BatchUGC(List<UgcDO> ugcDOS, List<UGCVO> ugcvos) {
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
            ugcvo.setAttachment_list(attachmentDtos);
            voteLogDOExample voteLogDOExample = new voteLogDOExample();
            com.hznu.xdd.domain.pojoExam.voteLogDOExample.Criteria criteria = voteLogDOExample.createCriteria();
            criteria.andUser_idEqualTo(txt.getUser_id());
            criteria.andVote_to_idEqualTo(txt.getId());
            if (voteLogDOMapper.selectByExample(voteLogDOExample).size() != 0){
                ugcvo.setIs_vote(true);
            }else {
                ugcvo.setIs_vote(false);
            }
            collectLogDOExample collectLogDOExample = new collectLogDOExample();
            com.hznu.xdd.domain.pojoExam.collectLogDOExample.Criteria criteria3 = collectLogDOExample.createCriteria();
            criteria3.andCollect_to_idEqualTo(txt.getId());
            criteria3.andUser_idEqualTo(txt.getUser_id());
            if (collectLogDOMapper.selectByExample(collectLogDOExample).size() != 0){
                ugcvo.setIs_collect(true);
            }else {
                ugcvo.setIs_collect(false);
            }
            UserDO userDO = userDOMapper.selectByPrimaryKey(txt.getUser_id());
            UserVO userVO = new UserVO();
            userVO.setId(userDO.getId());
            userVO.setAvatar(userDO.getAvatar());
            userVO.setNickname(userDO.getNickname());
            userVO.setGender(userDO.getGender());
            userVO.setRole(userDO.getRole());
            ugcvo.setUser(userVO);
            ugcvo.setScore(txt.getExposure() * 0.2 + txt.getVote() * 0.3 + txt.getComment() * 0.5);
            ugcvos.add(ugcvo);
        });
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
        ugcCommentDO.setUgc_id(to_id);
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
        System.out.println(voteLogDOMapper.selectByExample(voteLogDOExample));
        if (voteLogDOMapper.selectByExample(voteLogDOExample).size() == 0){
            voteLogDO voteLogDO = new voteLogDO();
            voteLogDO.setUpdate_time(new Date());
            voteLogDO.setCreate_time(new Date());
            voteLogDO.setVote_to_id(to_id);
            voteLogDO.setVote_type("ugc");
            voteLogDO.setIs_delete(status);
            voteLogDO.setUser_id(user_id);
            count = voteLogDOMapper.insert(voteLogDO);
        }else {
            List<voteLogDO> voteLogDOS = voteLogDOMapper.selectByExample(voteLogDOExample);
            voteLogDO voteLogDO = voteLogDOS.get(0);
            voteLogDO.setUpdate_time(new Date());
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
        if (collectLogDOMapper.selectByExample(collectLogDOExample).size() == 0){
            collectLogDO collectLogDO = new collectLogDO();
            collectLogDO.setCreate_time(new Date());
            collectLogDO.setUpdate_time(new Date());
            collectLogDO.setCollect_to_id(to_id);
            collectLogDO.setCollect_type("ugc");
            collectLogDO.setIs_delete(status);
            collectLogDO.setUser_id(user_id);
            count = collectLogDOMapper.insert(collectLogDO);
        }else {
            List<collectLogDO> collectLogDOs = collectLogDOMapper.selectByExample(collectLogDOExample);
            collectLogDO collectLogDO = collectLogDOs.get(0);
            collectLogDO.setIs_delete(status);
            collectLogDO.setUpdate_time(new Date());
            count = collectLogDOMapper.updateByPrimaryKey(collectLogDO);
        }
        return count;
    }

    /**
     * 获取UGC评论并生成评论树
     * @param id
     * @return
     */
    @Override
    public List<CommentVO> getCommentById(Integer id) {
        ugcCommentDOExample ugcCommentDOExample = new ugcCommentDOExample();
        com.hznu.xdd.domain.pojoExam.ugcCommentDOExample.Criteria criteria = ugcCommentDOExample.createCriteria();
        criteria.andUgc_idEqualTo(id);
        //获取所有评论
        List<ugcCommentDO> ugcCommentDOS = ugcCommentDOMapper.selectByExample(ugcCommentDOExample);
        List<CommentVO> commentVOS = new ArrayList<CommentVO>();
        ugcCommentDOS.forEach((txt)->{
            if (txt.getParent_id() == -1){
                UserDOExample userDOExample = new UserDOExample();
                UserDOExample.Criteria criteria1 = userDOExample.createCriteria();
                criteria1.andIdEqualTo(txt.getUser_id());
                List<UserDO> userDOS = userDOMapper.selectByExample(userDOExample);
                CommentVO commentVO = new CommentVO();
                commentVO.setCreate_time(txt.getCreate_time());
                commentVO.setVote_num(txt.getVote());
                commentVO.setUserVO(new UserVO().setAvatar(userDOS.get(0).getAvatar()).setNickname(userDOS.get(0).getNickname()).setId(txt.getUser_id()));
                commentVO.setContent(txt.getContent());
                commentVO.setId(txt.getId());
                commentVOS.add(commentVO);
            }else {
                setChild(commentVOS,txt);
            }
        });
        return commentVOS;
    }

    @Override
    public ListVO getTopic(Integer page, Integer offset) {
        topicDOExample topicDOExample = new topicDOExample();
        com.hznu.xdd.domain.pojoExam.topicDOExample.Criteria criteria = topicDOExample.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        int size = topicDOMapper.selectByExample(topicDOExample).size();
        topicDOExample.page(page,offset);
        List<topicDO> topicDOS = topicDOMapper.selectByExample(topicDOExample);
        return new ListVO().setList(topicDOS).setTotal(size);
    }

    @Override
    public ListVO getLabel(Integer page, Integer offset) {
        labelDOExample labelDOExample = new labelDOExample();
        com.hznu.xdd.domain.pojoExam.labelDOExample.Criteria criteria = labelDOExample.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        int size = labelDOMapper.selectByExample(labelDOExample).size();
        labelDOExample.page(page,offset);
        List<labelDO> labelDOS = labelDOMapper.selectByExample(labelDOExample);
        return new ListVO().setList(labelDOS).setTotal(size);
    }

    private void setChild(List<CommentVO> commentVOS,ugcCommentDO ugcCommentDO){
        for (int i = 0; i < commentVOS.size(); i++) {
            if (ugcCommentDO.getParent_id().equals(commentVOS.get(i).getId())){
                UserDOExample userDOExample = new UserDOExample();
                UserDOExample.Criteria criteria1 = userDOExample.createCriteria();
                criteria1.andIdEqualTo(ugcCommentDO.getUser_id());
                List<UserDO> userDOS = userDOMapper.selectByExample(userDOExample);
                CommentVO commentVO = new CommentVO();
                commentVO.setId(ugcCommentDO.getId());
                commentVO.setCreate_time(ugcCommentDO.getCreate_time());
                commentVO.setVote_num(ugcCommentDO.getVote());
                commentVO.setUserVO(new UserVO().setAvatar(userDOS.get(0).getAvatar()).setNickname(userDOS.get(0).getNickname()).setId(ugcCommentDO.getUser_id()));
                commentVO.setContent(ugcCommentDO.getContent());
            }
        }
    }

}
