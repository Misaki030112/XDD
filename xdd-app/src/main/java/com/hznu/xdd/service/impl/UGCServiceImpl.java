package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.hznu.xdd.dao.UgcDOMapper;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.Dto.attachmentDto;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.domain.pojoExam.UgcDOExample;
import com.hznu.xdd.pojo.UgcDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UGCService;
import com.hznu.xdd.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UGCServiceImpl implements UGCService {

    @Autowired
    UgcDOMapper ugcDOMapper;

    @Autowired
    UserDOMapper userDOMapper;

    @Override
    public List<UgcDO> listAllUGC() {
        UgcDOExample ugcDOExample = new UgcDOExample();
        UgcDOExample.Criteria criteria = ugcDOExample.createCriteria();
        criteria.andIdIsNotNull();
        return ugcDOMapper.selectByExample(ugcDOExample);
    }

    @Override
    public UgcDO findById(Integer id) {
        return ugcDOMapper.selectByPrimaryKey(id);
    }

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

    @Override
    public Integer deleteUGC(Integer id) {
        return ugcDOMapper.deleteByPrimaryKey(id);
    }

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

    @Override
    public List<UGCVO> listPublishUGCById(Integer id, String key, String label, String topic, String order_by) {
        UgcDOExample ugcDOExample = new UgcDOExample();
        UgcDOExample.Criteria criteria1 = ugcDOExample.createCriteria();
        UgcDOExample.Criteria criteria2 = ugcDOExample.createCriteria();
        criteria1.andUser_idEqualTo(id);
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
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        ugcDOS.forEach((txt)->{
            UGCVO ugcvo = new UGCVO();
            BeanUtils.copyProperties(txt,ugcvo);
            List<attachmentDto> attachmentDtos = new ArrayList<attachmentDto>();
            if (txt.getImages() != null){
                String[] split = txt.getImages().split(",");
                for (String s : split) {
                    attachmentDto attachmentDto = new attachmentDto();
                    attachmentDto.setAttachment_type("image").setAttachment_url(s);
                    attachmentDtos.add(attachmentDto);
                }
            }
            if (txt.getVideo() != null){
                String[] split = txt.getVideo().split(",");
                for (String s : split) {
                    attachmentDto attachmentDto = new attachmentDto();
                    attachmentDto.setAttachment_type("video").setAttachment_url(s);
                    attachmentDtos.add(attachmentDto);
                }
            }
            ugcvo.set_collect(true);
            ugcvo.set_vote(true);
            userDO.setId(ugcvo.getId());
            BeanUtils.copyProperties(userDO,ugcvo.getUser());
            ugcvos.add(ugcvo);
        });
        return ugcvos;
    }
}
