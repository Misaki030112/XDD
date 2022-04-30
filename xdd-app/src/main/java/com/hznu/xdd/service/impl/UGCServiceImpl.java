package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.dao.UgcDOMapper;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.dao.ugcCommentDOMapper;
import com.hznu.xdd.dao.voteLogDOMapper;
import com.hznu.xdd.dao.collectLogDOMapper;
import com.hznu.xdd.dao.topicDOMapper;
import com.hznu.xdd.dao.labelDOMapper;
import com.hznu.xdd.dao.searchLogDOMapper;
import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.Dto.attachmentDto;
import com.hznu.xdd.domain.Dto.locationDto;
import com.hznu.xdd.domain.VO.CommentVO;
import com.hznu.xdd.domain.VO.UgcPageVO;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.domain.VO.UserVO;
import com.hznu.xdd.domain.pojoExam.*;
import com.hznu.xdd.pojo.*;
import com.hznu.xdd.service.UGCService;
import com.hznu.xdd.util.ContentUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UGCServiceImpl implements UGCService {

    @Resource
    UgcDOMapper ugcDOMapper;

    @Resource
    UserDOMapper userDOMapper;

    @Resource
    ugcCommentDOMapper ugcCommentDOMapper;

    @Resource
    voteLogDOMapper voteLogDOMapper;

    @Resource
    collectLogDOMapper collectLogDOMapper;

    @Resource
    topicDOMapper topicDOMapper;

    @Resource
    labelDOMapper labelDOMapper;

    @Resource
    searchLogDOMapper searchLogDOMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    RestTemplate restTemplate;

    /**
     * 获取所有评论
     * @return ugc列表
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
     * @param id ugcid
     * @return ugc
     */
    @Override
    public UgcDO findById(Integer id) {
        return ugcDOMapper.selectByPrimaryKey(id);
    }

    /**
     * 新建一个评论
     * @param UGCDto ugc
     * @return 是否成功
     */
    @Override
    public Integer createUGC(UGCDto UGCDto) {
        UgcDO ugcDO = new UgcDO();
        Date date = new Date();
        ugcDO.setCreate_time(date);
        ugcDO.setUpdate_time(date);
        System.out.println(UGCDto.getAttachment());
        mergeUGCAttachment(UGCDto, ugcDO);
        if (UGCDto.getTopic() != null){
            ugcDO.setTopic(UGCDto.getTopic());
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

    private void mergeUGCAttachment(UGCDto UGCDto, UgcDO ugcDO) {
        if (UGCDto.getAttachment() != null){
            UGCDto.getAttachment().forEach(attachment -> {
                if (attachment.getAttachment_type().equals("image")){
                    if (ugcDO.getImages() != null){
                        ugcDO.setImages(ugcDO.getImages() + ',' + attachment.getAttachment_url());
                    }else{
                        ugcDO.setImages(attachment.getAttachment_url());
                    }

                }else {
                    if (ugcDO.getVideo() != null){
                        ugcDO.setVideo(ugcDO.getVideo() + ',' + attachment.getAttachment_url());
                    }else{
                        ugcDO.setVideo(attachment.getAttachment_url());
                    }

                }
            });
        }
        if (UGCDto.getTitle() != null){
            ugcDO.setTitle(UGCDto.getTitle());
        }
    }


    /**
     * 删除一个评论
     * @param id ugcid
     * @return 是否成功
     */
    @Override
    public Integer deleteUGC(Integer id) {
        UgcDO ugcDO = ugcDOMapper.selectByPrimaryKey(id);
        ugcDO.setIs_delete(true);
        return ugcDOMapper.updateByPrimaryKeySelective(ugcDO);
    }

    /**
     * 更新一条评论
     * @param UGCDto ugc
     * @return 是否成功
     */
    @Override
    public Integer updateUGC(UGCDto UGCDto) {
        UgcDO ugcDO = ugcDOMapper.selectByPrimaryKey(UGCDto.getId());
        ugcDO.setImages(null);
        ugcDO.setVideo(null);
        mergeUGCAttachment(UGCDto, ugcDO);
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
        Date date = new Date();
        ugcDO.setUpdate_time(date);
        return ugcDOMapper.updateByPrimaryKey(ugcDO);
    }

    /**
     * 获取UGC
     * @param user_id UGC的用户id
     * @param key 搜索关键词
     * @param label 搜索标签
     * @param topic 搜索主题
     * @param order_by 排序规则
     * @param page 第几页
     * @param offset 偏移量
     * @param fun 方式
     * @param uid 登录用户id
     * @return UGC列表
     */
    @Override
    public UgcPageVO listPublishUGCById(Integer user_id, String key, String label, String topic, String order_by, Integer page, Integer offset , Integer fun,Integer uid) {
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
            List<Integer> integers = new ArrayList<>();
            voteLogDOS.forEach(txt->
                integers.add(txt.getVote_to_id())
            );
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
            List<Integer> integers = new ArrayList<>();
            collectLogDOS.forEach(txt->
                integers.add(txt.getCollect_to_id())
            );
            if (integers.size() == 0){
                return null;
            }
            criteria1.andIdIn(integers);
            criteria2.andIdIn(integers);
        }else if (fun == 3 && user_id != null){
            criteria1.andIdEqualTo(user_id);
            criteria2.andIdEqualTo(user_id);
            UgcDO ugcDO = ugcDOMapper.selectByPrimaryKey(user_id);
            ugcDO.setExposure(ugcDO.getExposure() + 1);
            ugcDOMapper.updateByPrimaryKeySelective(ugcDO);
        }
        criteria1.andIs_deleteEqualTo(false);
        criteria2.andIs_deleteEqualTo(false);
        criteria1.andIs_onlineEqualTo(true);
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
        ugcDOExample.setOrderByClause("create_time desc");
        int size = ugcDOMapper.selectByExample(ugcDOExample).size();
        if (page != null && offset != null){
            ugcDOExample.page(page,offset);
        }
        List<UGCVO> ugcvos = new ArrayList<>();
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(ugcDOExample);
        BatchUGC(ugcDOS, ugcvos,uid);
        return new UgcPageVO().setList(ugcvos).setTotal(size);
    }

    /**
     * 获取热门帖子
     * @param page 第几页
     * @param offset 偏移量
     * @return UGC列表
     */
    @Override
    public UgcPageVO getHotUGC(Integer page, Integer offset,Integer user_id) {
        UgcDOExample ugcDOExample = new UgcDOExample();
        int size = ugcDOMapper.selectByExample(ugcDOExample).size();
//        ugcDOExample.page(page,offset);
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(ugcDOExample);
        List<UGCVO> ugcvos = new ArrayList<>();
        BatchUGC(ugcDOS, ugcvos,user_id);
        ugcvos.sort((o1, o2) -> o2.getScore().compareTo(o1.getScore()));
        ugcvos = ugcvos.subList(page*offset,(page + 1) *offset - 1);
        return new UgcPageVO().setList(ugcvos).setTotal(size);
    }

    private void BatchUGC(List<UgcDO> ugcDOS, List<UGCVO> ugcvos,Integer user_id) {
        ugcDOS.forEach((txt)->{
            UGCVO ugcvo = new UGCVO();
            List<attachmentDto> attachmentDtos = new ArrayList<>();
            if ( txt.getImages() != null && !txt.getImages().equals("") ){
                String[] split = txt.getImages().split(",");
                for (String s : split) {
                    attachmentDto attachmentDto = new attachmentDto();
                    attachmentDto.setAttachment_type("image").setAttachment_url(s);
                    attachmentDtos.add(attachmentDto);
                }
            }
            if (txt.getVideo() != null && !txt.getVideo().equals("")) {
                String[] split = txt.getVideo().split(",");
                for (String s : split) {
                    attachmentDto attachmentDto = new attachmentDto();
                    attachmentDto.setAttachment_type("video").setAttachment_url(s);
                    attachmentDtos.add(attachmentDto);
                }
            }
            locationDto object = JSONObject.parseObject(txt.getLocation(),locationDto.class) ;
            ugcvo.setLocation(object);
            ugcvo.setId(txt.getId());
            ugcvo.setCreate_time(txt.getCreate_time());
            ugcvo.setUpdate_time(txt.getUpdate_time());
            ugcvo.setVote(txt.getVote());
            ugcvo.setComment(txt.getComment());
            if(!txt.getLabel().equals(""))ugcvo.setLabel(Arrays.asList(txt.getLabel().split(",")));
            ugcvo.setTopic(txt.getTopic());
            ugcvo.setContent(txt.getContent());
            ugcvo.setTitle(txt.getTitle());
            ugcvo.setAttachment_list(attachmentDtos);
            voteLogDOExample voteLogDOExample = new voteLogDOExample();
            com.hznu.xdd.domain.pojoExam.voteLogDOExample.Criteria criteria = voteLogDOExample.createCriteria();
            criteria.andUser_idEqualTo(user_id);
            criteria.andVote_to_idEqualTo(txt.getId());
            criteria.andIs_deleteEqualTo(false);
            ugcvo.setIs_vote(voteLogDOMapper.selectByExample(voteLogDOExample).size() != 0);
            collectLogDOExample collectLogDOExample = new collectLogDOExample();
            com.hznu.xdd.domain.pojoExam.collectLogDOExample.Criteria criteria3 = collectLogDOExample.createCriteria();
            criteria3.andCollect_to_idEqualTo(txt.getId());
            criteria3.andUser_idEqualTo(user_id);
            criteria3.andIs_deleteEqualTo(false);
            ugcvo.setIs_collect(collectLogDOMapper.selectByExample(collectLogDOExample).size() != 0);
            UserDO userDO = userDOMapper.selectByPrimaryKey(txt.getUser_id());
            UserVO userVO = new UserVO();
            userVO.setId(userDO.getId());
            userVO.setAvatar(userDO.getAvatar());
            userVO.setNickname(userDO.getNickname());
            userVO.setGender(userDO.getGender());
            userVO.setRole(userDO.getRole());
            ugcvo.setUser_info(userVO);
            ugcvo.setScore(txt.getExposure() * 0.2 + txt.getVote() * 0.3 + txt.getComment() * 0.5);
            ugcvos.add(ugcvo);
        });
    }

    /**
     * 评论UGC
     * @param content 评论内容
     * @param parent_id 评论的父级id
     * @param to_type 评论对象的类型
     * @param to_id 评论的id
     * @param user_id 评论者id
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addComment(String content, Integer parent_id, String to_type, Integer to_id, Integer user_id) {
        ArrayList<Integer> userIdList = new ArrayList<>(); //需要发送用户id列表
        int parent = parent_id;
        while (parent != -1){
            ugcCommentDO ugcItem = ugcCommentDOMapper.selectByPrimaryKey(parent);
            userIdList.add(ugcItem.getUser_id());
            parent = ugcItem.getParent_id();
        }
        userIdList.add(ugcDOMapper.selectByPrimaryKey(to_id).getUser_id());
        UgcDO ugcDO = ugcDOMapper.selectByPrimaryKey(to_id);
        ugcDO.setComment(ugcDO.getComment() + 1);
        ugcDOMapper.updateByPrimaryKeySelective(ugcDO);
        ugcCommentDO ugcCommentDO = new ugcCommentDO();
        Date date = new Date();
        ugcCommentDO.setCreate_time(date);
        ugcCommentDO.setUpdate_time(date);
        ugcCommentDO.setContent(content);
        ugcCommentDO.setParent_id(parent_id);
        ugcCommentDO.setIs_delete(false);
        ugcCommentDO.setUser_id(user_id);
        ugcCommentDO.setUgc_id(to_id);
        ContentUtil.sendMessage(content,restTemplate,userIdList,userDOMapper,to_id,user_id,ugcCommentDO,"c_eXThmZYQ1GXjpygjzRD2lBZYGOR8L6WdbB1HwO1_o");
        return ugcCommentDOMapper.insert(ugcCommentDO);
    }

    /**
     * 点赞UGC
     * @param to_id ugcid
     * @param status 状态
     * @param user_id 点赞人id
     * @return 是否成功
     */
    @Override
    public synchronized Integer voteUGC(Integer to_id, boolean status,Integer user_id) {
        int count;
        voteLogDOExample voteLogDOExample = new voteLogDOExample();
        com.hznu.xdd.domain.pojoExam.voteLogDOExample.Criteria criteria = voteLogDOExample.createCriteria();
        criteria.andUser_idEqualTo(user_id);
        criteria.andVote_to_idEqualTo(to_id);
        UgcDO ugcDO = ugcDOMapper.selectByPrimaryKey(to_id);
        List<voteLogDO> voteLogDOS = voteLogDOMapper.selectByExample(voteLogDOExample);
        if (voteLogDOS.size() == 0){
            voteLogDO voteLogDO = new voteLogDO();
            voteLogDO.setUpdate_time(new Date());
            voteLogDO.setCreate_time(new Date());
            voteLogDO.setVote_to_id(to_id);
            voteLogDO.setVote_type("ugc");
            voteLogDO.setIs_delete(!status);
            voteLogDO.setUser_id(user_id);
            count = voteLogDOMapper.insert(voteLogDO);
            if (status){
                ugcDO.setVote(ugcDO.getVote() + 1);
            }else{
                ugcDO.setVote(ugcDO.getVote() - 1);
            }
        }else if (status && voteLogDOS.get(0).getIs_delete() || !status && !voteLogDOS.get(0).getIs_delete()){
            int n = status ? 1 : -1;
            ugcDO.setVote(ugcDO.getVote() + n);
            ugcDOMapper.updateByPrimaryKey(ugcDO);
            voteLogDO voteLogDO = voteLogDOS.get(0);
            voteLogDO.setUpdate_time(new Date());
            voteLogDO.setIs_delete(!status);
            count = voteLogDOMapper.updateByPrimaryKey(voteLogDO);
        }else{
            count = 0;
        }
        return count;
    }

    /**
     * 收藏UGC
     * @param to_id UGCid
     * @param status 状态
     * @param user_id 收藏者id
     * @return 是否成功
     */
    @Override
    public synchronized Integer collectUGC(Integer to_id, boolean status, Integer user_id) {
        int count;
        collectLogDOExample collectLogDOExample = new collectLogDOExample();
        com.hznu.xdd.domain.pojoExam.collectLogDOExample.Criteria criteria = collectLogDOExample.createCriteria();
        criteria.andUser_idEqualTo(user_id);
        criteria.andCollect_to_idEqualTo(to_id);
        UgcDO ugcDO = ugcDOMapper.selectByPrimaryKey(to_id);
        List<collectLogDO> collectLogDOs = collectLogDOMapper.selectByExample(collectLogDOExample);
        if (collectLogDOs.size() == 0){
            collectLogDO collectLogDO = new collectLogDO();
            collectLogDO.setCreate_time(new Date());
            collectLogDO.setUpdate_time(new Date());
            collectLogDO.setCollect_to_id(to_id);
            collectLogDO.setCollect_type("ugc");
            collectLogDO.setIs_delete(!status);
            collectLogDO.setUser_id(user_id);
            if (status){
                ugcDO.setCollect(ugcDO.getCollect() + 1);
            }else{
                ugcDO.setCollect(ugcDO.getCollect() - 1);
            }
            count = collectLogDOMapper.insert(collectLogDO);
        }else if(status && collectLogDOs.get(0).getIs_delete() || !status && !collectLogDOs.get(0).getIs_delete()){
            if (status){
                ugcDO.setCollect(ugcDO.getCollect() + 1);
            }else{
                ugcDO.setCollect(ugcDO.getCollect() - 1);
            }
            ugcDOMapper.updateByPrimaryKey(ugcDO);
            collectLogDO collectLogDO = collectLogDOs.get(0);
            collectLogDO.setIs_delete(!status);
            collectLogDO.setUpdate_time(new Date());
            count = collectLogDOMapper.updateByPrimaryKey(collectLogDO);
        }else {
            count = 0;
        }
        return count;
    }

    /**
     * 获取UGC评论并生成评论树
     * @param id ugcid
     * @return 评论树
     */
    @Override
    public List<CommentVO> getCommentById(Integer id) {
        ugcCommentDOExample ugcCommentDOExample = new ugcCommentDOExample();
        com.hznu.xdd.domain.pojoExam.ugcCommentDOExample.Criteria criteria = ugcCommentDOExample.createCriteria();
        criteria.andUgc_idEqualTo(id);
        //获取所有评论
        List<ugcCommentDO> ugcCommentDOS = ugcCommentDOMapper.selectByExample(ugcCommentDOExample);
        ArrayList<CommentVO> commentVOS = new ArrayList<>();
        ugcCommentDOS.forEach((item) -> {
            UserDOExample userDOExample = new UserDOExample();
            UserDOExample.Criteria criteria1 = userDOExample.createCriteria();
            criteria1.andIdEqualTo(item.getUser_id());
            List<UserDO> userDOS = userDOMapper.selectByExample(userDOExample);
            CommentVO commentVO = new CommentVO();
            commentVO.setCreate_time(item.getCreate_time());
            commentVO.setVote_num(item.getVote());
            commentVO.setUser_info(new UserVO().setAvatar(userDOS.get(0).getAvatar()).setNickname(userDOS.get(0).getNickname()).setId(item.getUser_id()));
            commentVO.setContent(item.getContent());
            commentVO.setId(item.getId());
            commentVO.setParent_id(item.getParent_id());
            commentVOS.add(commentVO);
        });
        return createTree(commentVOS);
    }

    public List<CommentVO> createTree(List<CommentVO> list){
        List<CommentVO> commentVOS = new ArrayList<>();
        for (CommentVO commentVO : list) {
            if (commentVO.getParent_id() != -1){
                list.forEach((item)->{
                    if (item.getId().equals(commentVO.getParent_id())) commentVO.setParent_comment(item);
                });
            }
            commentVOS.add(commentVO);
        }
        return commentVOS;
    }


    /**
     * 获取主题
     * @param page 第几页
     * @param offset 偏移量
     * @return topic列表
     */
    @Override
    public UgcPageVO getTopic(Integer page, Integer offset) {
        topicDOExample topicDOExample = new topicDOExample();
        com.hznu.xdd.domain.pojoExam.topicDOExample.Criteria criteria = topicDOExample.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        int size = topicDOMapper.selectByExample(topicDOExample).size();
        topicDOExample.page(page,offset);
        List<topicDO> topicDOS = topicDOMapper.selectByExample(topicDOExample);
        return new UgcPageVO().setList(topicDOS).setTotal(size);
    }

    /**
     * 获取标签
     * @param page 第几页
     * @param offset 偏移量
     * @return label列表
     */
    @Override
    public UgcPageVO getLabel(Integer page, Integer offset) {
        labelDOExample labelDOExample = new labelDOExample();
        com.hznu.xdd.domain.pojoExam.labelDOExample.Criteria criteria = labelDOExample.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        int size = labelDOMapper.selectByExample(labelDOExample).size();
        labelDOExample.page(page,offset);
        List<labelDO> labelDOS = labelDOMapper.selectByExample(labelDOExample);
        return new UgcPageVO().setList(labelDOS).setTotal(size);
    }


    public static long NumberOfDaysEndUnixTime(int NumberOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)-NumberOfDays,23,59,59);
        long yesterdayEnd = calendar.getTimeInMillis();
        return yesterdayEnd;
    }
    /**
     * 保存用户搜索记录以及存入到redis中便于热搜
     * @param key 搜索关键词
     * @param user_id 搜索人id
     * @return 是否成功
     */
    @Override
    public Integer saveSearch(String key, Integer user_id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        //七天热搜
        long timeOut = (calendar.getTimeInMillis()-NumberOfDaysEndUnixTime(7)) / 1000;
        redisTemplate.expire("hotWord",timeOut, TimeUnit.SECONDS);
        redisTemplate.opsForZSet().incrementScore("hotWord", key, 1); // 加入排序set
        searchLogDO searchLogDO = new searchLogDO();
        searchLogDO.setCreate_time(new Date());
        searchLogDO.setIs_delete(false);
        searchLogDO.setUser_id(user_id);
        searchLogDO.setUpdate_time(new Date());
        searchLogDO.setContent(key);
        return searchLogDOMapper.insert(searchLogDO);
    }


}
