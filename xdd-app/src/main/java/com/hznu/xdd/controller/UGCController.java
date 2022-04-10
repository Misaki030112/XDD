package com.hznu.xdd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.VO.CommentVO;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.pojo.UgcDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.pojo.topicDO;
import com.hznu.xdd.service.UGCService;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.ContentUtil;
import com.hznu.xdd.util.UserInfoUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class UGCController {
    @Autowired
    UserService userService;

    @Autowired
    UGCService ugcService;

    @Resource
    RestTemplate restTemplate;
    @GetMapping(value = "/get/ugc",produces = {"application/json;charset=UTF-8"})
    public Result getAllUGC(@RequestParam(value = "id",required = false) Integer id,
                            @RequestParam(value = "key",required = false) String key,
                            @RequestParam(value = "label",required = false) String label,
                            @RequestParam(value = "topic",required = false) String topic,
                            @RequestParam(value = "order_by",required = false) String order_by,
                            @RequestParam(value = "page") Integer page,
                            @RequestParam(value = "offset") Integer offset
    ){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(id, key, label, topic, order_by,
                page,offset,3);
        return Result.ok(ugcvoList,"获取成功");
    }


    @PostMapping(value = "/post/ugc/create",produces = {"application/json;charset=UTF-8"})
    public Result createUGC(@RequestBody UGCDto UGCDto,
                            Authentication authentication){
        if (ContentUtil.ContentCheck(authentication,UGCDto.getContent(),restTemplate)){
            UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
            UGCDto.setUser_id(userDO.getId());
            Integer count = ugcService.createUGC(UGCDto);
            return Result.ok(count,"创建成功");
        }else {
            return new Result(StatusCode.CONTENT_INVALID);
        }

    }

    @PostMapping(value = "/post/ugc/delete",produces = {"application/json;charset=UTF-8"})
    public Result deleteUGC(@RequestBody UGCDto ugcDto,
                            Authentication authentication){
        UgcDO ugc = ugcService.findById(ugcDto.getId());
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if (!userDO.getId().equals(ugc.getUser_id())){
            return new Result(StatusCode.VISIT_INVALID);
        }
        Integer deleteUGC = ugcService.deleteUGC(ugcDto.getId());
        return Result.ok(deleteUGC,"删除成功");
    }

    @PostMapping(value = "/post/ugc/update",produces = {"application/json;charset=UTF-8"})
    public Result updateUGC(@RequestBody UGCDto UGCDto,
                            Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcDO ugcDO = ugcService.findById(UGCDto.getId());
        if(!userDO.getId().equals(ugcDO.getUser_id())){
            return new Result(StatusCode.VISIT_INVALID);
        }else if (ContentUtil.ContentCheck(authentication,UGCDto.getContent(),restTemplate)){
            Integer integer = ugcService.updateUGC(UGCDto);
            return Result.ok(integer,"更新成功");
        }else {
            return new Result(StatusCode.CONTENT_INVALID);
        }

    }

    @GetMapping(value = "/get/ugc/user/publish",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByPublish(@RequestParam("user_id") Integer user_id,
                                      @RequestParam(value = "key",required = false) String key,
                                      @RequestParam(value = "label",required = false) String label,
                                      @RequestParam(value = "topic",required = false) String topic,
                                      @RequestParam(value = "order_by",required = false) String order_by,
                                      @RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "offset") Integer offset
                                      ){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(user_id, key,label, topic, order_by,
                page,offset,1);
        return Result.ok(ugcvoList,"获取成功");
    }

    @GetMapping(value = "/get/ugc/user/vote",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByVote(@RequestParam("user_id") Integer user_id,
                                   @RequestParam(value = "key",required = false) String key,
                                   @RequestParam(value = "label",required = false) String label,
                                   @RequestParam(value = "topic",required = false) String topic,
                                   @RequestParam(value = "order_by",required = false) String order_by,
                                   @RequestParam(value = "page") Integer page,
                                   @RequestParam(value = "offset") Integer offset){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(user_id, key, label, topic, order_by,
                page,offset,2);
        return Result.ok(ugcvoList,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/vote",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByVote(@RequestBody UGCDto ugcDto,
                                   Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(userDO.getId(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),2);
        return Result.ok(ugcvoList,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/publish",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByPublish(@RequestBody UGCDto ugcDto,
                                      Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(userDO.getId(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),1);
        return Result.ok(ugcvoList,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/collect",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByCollect(@RequestBody UGCDto ugcDto,
                                      Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(userDO.getId(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),4);
        return Result.ok(ugcvoList,"获取成功");
    }

    @PostMapping(value = "/post/ugc/comment",produces = {"application/json;charset=UTF-8"})
    public Result addComment(@RequestBody UGCDto ugcDto,
                             Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if (ContentUtil.ContentCheck(authentication,ugcDto.getContent(),restTemplate)){
            Integer count = ugcService.addComment(ugcDto.getContent(),ugcDto.getParent_id(),ugcDto.getTo_type(),ugcDto.getTo_id(),userDO.getId());
            return Result.ok(count,"评论成功");
        }else {
            return new Result(StatusCode.CONTENT_INVALID);
        }

    }

    @PostMapping(value = "/post/ugc/vote",produces = {"application/json;charset=UTF-8"})
    public Result voteUGC(@RequestBody UGCDto ugcDto,
                          Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        return Result.ok(ugcService.voteUGC(ugcDto.getTo_id(),ugcDto.isStatus(),userDO.getId()),"点赞成功");
    }

    @PostMapping(value = "/post/ugc/collect",produces = {"application/json;charset=UTF-8"})
    public Result collectUGC(@RequestBody UGCDto ugcDto,
                             Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        return Result.ok(ugcService.collectUGC(ugcDto.getTo_id(),ugcDto.isStatus(),userDO.getId()),"收藏成功");
    }

    @GetMapping(value = "/get/ugc/hot",produces = {"application/json;charset=UTF-8"})
    public Result getHotUGC(@RequestParam(value = "page") Integer page,
                            @RequestParam(value = "offset") Integer offset
    ){
        List<UGCVO> ugcvoList = ugcService.getHotUGC(page,offset);
        return Result.ok(ugcvoList,"获取成功");
    }

    @GetMapping(value = "/get/ugc/comment",produces = {"application/json;charset=UTF-8"})
    public Result getUGCComment(@RequestParam("id") Integer id){
        List<CommentVO> commentById = ugcService.getCommentById(id);
        return Result.ok(commentById,"获取成功");
    }

    @GetMapping(value = "/get/ugc/topic",produces = {"application/json;charset=UTF-8"})
    public Result getTopic(@RequestParam(value = "page") Integer page,
                           @RequestParam(value = "offset") Integer offset){
        List<topicDO> topic = ugcService.getTopic(page, offset);
        return Result.ok(topic,"获取成功");
    }
}
