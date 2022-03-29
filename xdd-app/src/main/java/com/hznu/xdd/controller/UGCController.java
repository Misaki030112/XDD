package com.hznu.xdd.controller;

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
    public Result getAllUGC(@RequestBody UGCDto ugcDto){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(ugcDto.getId(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),3);
        return Result.ok(ugcvoList,"获取成功");
    }


    @PostMapping(value = "/post/ugc/create",produces = {"application/json;charset=UTF-8"})
    public Result createUGC(@RequestBody UGCDto UGCDto,
                            Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UGCDto.setUser_id(userDO.getId());
        Integer count = ugcService.createUGC(UGCDto);
        return Result.ok(count,"创建成功");
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
        }
        Integer integer = ugcService.updateUGC(UGCDto);
        return Result.ok(integer,"更新成功");
    }

    @GetMapping(value = "/get/ugc/user/publish",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByPublish(@RequestBody UGCDto ugcDto){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(ugcDto.getUser_id(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),1);
        return Result.ok(ugcvoList,"获取成功");
    }

    @GetMapping(value = "/get/ugc/user/vote",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByVote(@RequestBody UGCDto ugcDto){
        List<UGCVO> ugcvoList = ugcService.listPublishUGCById(ugcDto.getUser_id(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),2);
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
        Integer count = ugcService.addComment(ugcDto.getContent(),ugcDto.getParent_id(),ugcDto.getTo_type(),ugcDto.getTo_id(),userDO.getId());
        return Result.ok(count,"评论成功");
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
    public Result getHotUGC(@RequestBody UGCDto ugcDto){
        List<UGCVO> ugcvoList = ugcService.getHotUGC(ugcDto.getPage(),ugcDto.getOffset());
        return Result.ok(ugcvoList,"获取成功");
    }

    @GetMapping(value = "/get/ugc/comment",produces = {"application/json;charset=UTF-8"})
    public Result getUGCComment(@RequestBody UGCDto ugcDto){
        List<CommentVO> commentById = ugcService.getCommentById(ugcDto.getId());
        return Result.ok(commentById,"获取成功");
    }

    @GetMapping(value = "/get/ugc/topic",produces = {"application/json;charset=UTF-8"})
    public Result getTopic(@RequestBody UGCDto ugcDto){
        List<topicDO> topic = ugcService.getTopic(ugcDto.getPage(), ugcDto.getOffset());
        return Result.ok(topic,"获取成功");
    }
}
