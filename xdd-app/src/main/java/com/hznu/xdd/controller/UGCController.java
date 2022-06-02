package com.hznu.xdd.controller;

import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.VO.CommentVO;
import com.hznu.xdd.domain.VO.UgcPageVO;
import com.hznu.xdd.pojo.UgcDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UGCService;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.ContentUtil;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Transactional(rollbackFor = Exception.class)
public class UGCController {
    
    private final UserService userService;
    private final UGCService ugcService;
    private final UserInfoUtil userInfoUtil;
    private final RestTemplate restTemplate;

    public UGCController(UserService userService, UGCService ugcService, UserInfoUtil userInfoUtil, RestTemplate restTemplate) {
        this.userService = userService;
        this.ugcService = ugcService;
        this.userInfoUtil = userInfoUtil;
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = {"/get/ugc"},produces = {"application/json;charset=UTF-8"})
    public Result getAllUGC(@RequestParam(value = "key",required = false) String key,
                            @RequestParam(value = "label",required = false) String label,
                            @RequestParam(value = "topic",required = false) String topic,
                            @RequestParam(value = "order_by",required = false) String order_by,
                            @RequestParam(value = "page") Integer page,
                            @RequestParam(value = "offset") Integer offset,
                            Authentication authentication
    ){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcPageVO vo = ugcService.listPublishUGCById(null, key, label, topic, order_by,
                page,offset,3,userDO.getId());
        if (key != null && !key.equals("")){
            ugcService.saveSearch(key,userDO.getId());
        }
        return Result.ok(vo,"获取成功");
    }

    @GetMapping(value = "/get/ugc/detail",produces = {"application/json;charset=UTF-8"})
    public Result getOneUGC(@RequestParam(value = "id") Integer id,Authentication authentication
    ){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcPageVO vo = ugcService.listPublishUGCById(id, null, null, null, null,
                null,null,3,userDO.getId());
        UgcPageVO.UGCVO o = (UgcPageVO.UGCVO) vo.getList().get(0);
        return Result.ok(o,"获取成功");
    }




    @PostMapping(value = "/post/ugc/create",produces = {"application/json;charset=UTF-8"})
    public Result createUGC(@RequestBody UGCDto UGCDto,
                            Authentication authentication){
        if (ContentUtil.ContentCheck(authentication,UGCDto.getContent(),restTemplate)){
            UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
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
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if (!userDO.getId().equals(ugc.getUser_id())){
            return new Result(StatusCode.VISIT_INVALID);
        }
        Integer deleteUGC = ugcService.deleteUGC(ugcDto.getId());
        return Result.ok(deleteUGC,"删除成功");
    }

    @PostMapping(value = "/post/ugc/update",produces = {"application/json;charset=UTF-8"})
    public Result updateUGC(@RequestBody UGCDto UGCDto,
                            Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
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
                                      @RequestParam(value = "offset") Integer offset,
                                      Authentication authentication
                                      ){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcPageVO vo = ugcService.listPublishUGCById(user_id, key, label, topic, order_by,
                page, offset, 1,userDO.getId());
        return Result.ok(vo,"获取成功");
    }

    @GetMapping(value = "/get/ugc/user/vote",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByVote(@RequestParam("user_id") Integer user_id,
                                   @RequestParam(value = "key",required = false) String key,
                                   @RequestParam(value = "label",required = false) String label,
                                   @RequestParam(value = "topic",required = false) String topic,
                                   @RequestParam(value = "order_by",required = false) String order_by,
                                   @RequestParam(value = "page") Integer page,
                                   @RequestParam(value = "offset") Integer offset,
                                   Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcPageVO vo = ugcService.listPublishUGCById(user_id, key, label, topic, order_by,
                page, offset, 2,userDO.getId());
        return Result.ok(vo,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/vote",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByVote(@RequestBody UGCDto ugcDto,
                                   Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcPageVO vo = ugcService.listPublishUGCById(userDO.getId(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),2,userDO.getId());
        return Result.ok(vo,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/publish",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByPublish(@RequestBody UGCDto ugcDto,
                                      Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcPageVO vo = ugcService.listPublishUGCById(userDO.getId(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),1,userDO.getId());
        return Result.ok(vo,"获取成功");
    }

    @PostMapping(value = "/post/ugc/my/collect",produces = {"application/json;charset=UTF-8"})
    public Result listAllUGCByCollect(@RequestBody UGCDto ugcDto,
                                      Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcPageVO vo = ugcService.listPublishUGCById(userDO.getId(), ugcDto.getKey(), ugcDto.getLabel(), ugcDto.getTopic(), ugcDto.getOrder_by(),
                ugcDto.getPage(),ugcDto.getOffset(),4,userDO.getId());
        return Result.ok(vo,"获取成功");
    }

    @PostMapping(value = "/post/ugc/comment",produces = {"application/json;charset=UTF-8"})
    public Result addComment(@RequestBody UGCDto ugcDto,
                             Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if (ContentUtil.ContentCheck(authentication,ugcDto.getContent(),restTemplate)){
            Integer count = ugcService.addComment(ugcDto.getContent(),ugcDto.getParent_id(),ugcDto.getTo_type(),ugcDto.getTo_id(),userDO.getId());
            return Result.ok(count,"评论成功");
        }else {
            return new Result(StatusCode.CONTENT_INVALID);
        }

    }

    @PostMapping(value = "/post/ugc/vote",produces = {"application/json;charset=UTF-8"})
    public  Result voteUGC(@RequestBody UGCDto ugcDto,
                          Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        boolean voteUGC = ugcService.voteUGC(ugcDto.getTo_id(), ugcDto.isStatus(), userDO.getId());
        if (!voteUGC){
            return new Result(StatusCode.INVALID_USER_PUBLISH);
        }else {
            return Result.ok(voteUGC,"点赞成功");
        }
    }

    @PostMapping(value = "/post/ugc/collect",produces = {"application/json;charset=UTF-8"})
    public  Result collectUGC(@RequestBody UGCDto ugcDto,
                             Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        boolean integer = ugcService.collectUGC(ugcDto.getTo_id(), ugcDto.isStatus(), userDO.getId());
        if (!integer){
            return new Result(StatusCode.INVALID_USER_PUBLISH);
        }else {
            return Result.ok(integer,"收藏成功");
        }
    }

    @GetMapping(value = "/get/ugc/hot",produces = {"application/json;charset=UTF-8"})
    public Result getHotUGC(@RequestParam(value = "page") Integer page,
                            @RequestParam(value = "offset") Integer offset,
                            Authentication authentication
    ){
        UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
        UgcPageVO vo = ugcService.getHotUGC(page,offset,userDO.getId());
        return Result.ok(vo,"获取成功");
    }

    @GetMapping(value = "/get/ugc/comment",produces = {"application/json;charset=UTF-8"})
    public Result getUGCComment(@RequestParam("id") Integer id){
        List<CommentVO> commentById = ugcService.getCommentById(id);
        return Result.ok(commentById,"获取成功");
    }

    @GetMapping(value = "/get/ugc/topic",produces = {"application/json;charset=UTF-8"})
    public Result getTopic(@RequestParam(value = "page") Integer page,
                           @RequestParam(value = "offset") Integer offset){
        UgcPageVO vo = ugcService.getTopic(page, offset);
        return Result.ok(vo,"获取成功");
    }

    @GetMapping(value = "/get/ugc/label",produces = {"application/json;charset=UTF-8"})
    public Result getLabel(@RequestParam(value = "page") Integer page,
                           @RequestParam(value = "offset") Integer offset){
        UgcPageVO vo = ugcService.getLabel(page, offset);
        return Result.ok(vo,"获取成功");
    }
}
