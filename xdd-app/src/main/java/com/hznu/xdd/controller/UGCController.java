package com.hznu.xdd.controller;

import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.pojo.UgcDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UGCService;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UGCController {
    @Autowired
    UserService userService;

    @Autowired
    UGCService ugcService;

    @GetMapping(value = "/get/ugc",produces = {"application/json;charset=UTF-8"})
    public Result getAllUGC(@RequestParam(value = "id",required = false) Integer id,
                            @RequestParam(value = "key", required = false) String key,
                            @RequestParam(value = "label", required = false) String label,
                            @RequestParam(value = "topic", required = false) String topic,
                            @RequestParam(value = "order_by", required = false) String order_by){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(id, key, label, topic, order_by,3);
        return Result.ok(ugcvoList,"获取成功");
    }


    @PostMapping(value = "/post/ugc/create",produces = {"application/json;charset=UTF-8"})
    public Result createUGC(@RequestBody UGCDto UGCDto,
                            Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UGCDto.setUserId(userDO.getId());
        Integer count = ugcService.createUGC(UGCDto);
        return Result.ok(count,"创建成功");
    }

    @PostMapping(value = "/post/ugc/delete",produces = {"application/json;charset=UTF-8"})
    public Result deleteUGC(@RequestParam("id") Integer id,
                            Authentication authentication){
        UgcDO ugc = ugcService.findById(id);
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if (!userDO.getId().equals(ugc.getUser_id())){
            return new Result(StatusCode.VISIT_INVALID);
        }
        Integer deleteUGC = ugcService.deleteUGC(id);
        return Result.ok(deleteUGC,"删除成功");
    }

    @PostMapping(value = "/post/ugc/update",produces = {"application/json;charset=UTF-8"})
    public Result updateUGC(@RequestBody UGCDto UGCDto,
                            Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcDO ugcDO = ugcService.findById(UGCDto.getId());
        if(!userDO.getId().equals(ugcDO.getUser_id())){
            return new Result(StatusCode.VISIT_INVALID);
        }
        Integer integer = ugcService.updateUGC(UGCDto);
        return Result.ok(integer,"更新成功");
    }

    @GetMapping(value = "/get/ugc/user/publish",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByPublish(@RequestParam(value = "user_id") Integer user_id,
                                 @RequestParam(value = "key", required = false) String key,
                                 @RequestParam(value = "label", required = false) String label,
                                 @RequestParam(value = "topic", required = false) String topic,
                                 @RequestParam(value = "order_by", required = false) String order_by){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(user_id, key, label, topic, order_by,1);
        return Result.ok(ugcvoList,"获取成功");
    }
    @GetMapping(value = "/get/ugc/user/vote",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByVote(@RequestParam(value = "user_id") Integer user_id,
                                 @RequestParam(value = "key", required = false) String key,
                                 @RequestParam(value = "label", required = false) String label,
                                 @RequestParam(value = "topic", required = false) String topic,
                                 @RequestParam(value = "order_by", required = false) String order_by){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(user_id, key, label, topic, order_by,2);
        return Result.ok(ugcvoList,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/vote",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByVote(@RequestParam(value = "key", required = false) String key,
                                   @RequestParam(value = "label", required = false) String label,
                                   @RequestParam(value = "topic", required = false) String topic,
                                   @RequestParam(value = "order_by", required = false) String order_by,
                                   Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(userDO.getId(), key, label, topic, order_by,2);
        return Result.ok(ugcvoList,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/publish",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByPublish(@RequestParam(value = "key", required = false) String key,
                                   @RequestParam(value = "label", required = false) String label,
                                   @RequestParam(value = "topic", required = false) String topic,
                                   @RequestParam(value = "order_by", required = false) String order_by,
                                   Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(userDO.getId(), key, label, topic, order_by,1);
        return Result.ok(ugcvoList,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/collect",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByCollect(@RequestParam(value = "key", required = false) String key,
                                      @RequestParam(value = "label", required = false) String label,
                                      @RequestParam(value = "topic", required = false) String topic,
                                      @RequestParam(value = "order_by", required = false) String order_by,
                                      Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(userDO.getId(), key, label, topic, order_by,4);
        return Result.ok(ugcvoList,"获取成功");
    }

    @PostMapping(value = "/post/ugc/comment",produces = {"application/json;charset=UTF-8"})
    public Result addComment(@RequestParam(value = "content") String content,
                             @RequestParam(value = "parent_id") Integer parent_id,
                             @RequestParam(value = "to_type") String to_type,
                             @RequestParam(value = "to_id") Integer to_id,
                             Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        Integer count = ugcService.addComment(content,parent_id,to_type,to_id,userDO.getId());
        return Result.ok(count,"评论成功");
    }

    @PostMapping(value = "/post/ugc/vote",produces = {"application/json;charset=UTF-8"})
    public Result voteUGC(Integer to_id,boolean status,
                          Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        return Result.ok(ugcService.voteUGC(to_id,status,userDO.getId()),"点赞成功");
    }

    @PostMapping(value = "/post/ugc/collect",produces = {"application/json;charset=UTF-8"})
    public Result collectUGC(Integer to_id,boolean status,
                          Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        return Result.ok(ugcService.collectUGC(to_id,status,userDO.getId()),"收藏成功");
    }
}
