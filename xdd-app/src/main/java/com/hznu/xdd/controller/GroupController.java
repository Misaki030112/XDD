package com.hznu.xdd.controller;

import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Dto.GroupDto;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.UserPageVO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.GroupService;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@Transactional(rollbackFor = Exception.class)
public class GroupController {
    private final GroupService groupService;
    private final UserInfoUtil userInfoUtil;
    private final  UserService userService;
    public GroupController(GroupService groupService, UserInfoUtil userInfoUtil, UserService userService) {
        this.groupService = groupService;
        this.userInfoUtil = userInfoUtil;
        this.userService = userService;
    }

    @PostMapping("/post/group/add")
    public Result addGroup(@RequestBody @Valid GroupDto groupDto){
        try {
            boolean flag = groupService.addGroup(groupDto);
            if(flag) return Result.ok(null,"添加成功");
            else return new Result(StatusCode.INVALID_USER_PUBLISH);
        }catch (Exception e){
           e.printStackTrace();
           return new Result(StatusCode.INVALID_USER_PUBLISH);
        }
    }
    

    @PostMapping("/post/group/delete")
    public Result deleteGroup(@RequestBody GroupDto groupDto){
        try {
            boolean flag = groupService.deleteGroup(groupDto.getId());
            if(flag) return Result.ok(null,"删除成功");
            else return new Result(StatusCode.INVALID_USER_PUBLISH);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(StatusCode.INVALID_USER_PUBLISH);
        }
    }

    


    @GetMapping("/get/group/user")
    public Result getGroupUser(@RequestParam("id") Integer id){
        List<UserPageVO.UserVO> groupUser = groupService.getGroupUser(id);
        return Result.ok(groupUser,"获取成功");
    }

    @PostMapping(value = "/post/group/end",produces = {"application/json;charset=UTF-8"})
    public Result groupEnd(@RequestParam("id") Integer id){
        try {
            boolean flag = groupService.groupEnd(id);
            if(flag) return Result.ok(null,"停止成功");
            else return new Result(StatusCode.INVALID_USER_PUBLISH);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(StatusCode.INVALID_USER_PUBLISH);
        }
    }

    @PostMapping(value = "/post/group/cancel",produces = {"application/json;charset=UTF-8"})
    public Result groupCancel(@RequestParam("id") Integer id,
                              Authentication authentication){
        try {
            UserDO userDO = userService.getUserByWxOpenId(userInfoUtil.getWxOpenIdXiaododoMini(authentication));
            Boolean flag = groupService.groupCancel(id, userDO.getId());
            if(flag) return Result.ok(null,"退出成功");
            else return new Result(StatusCode.INVALID_USER_PUBLISH);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(StatusCode.INVALID_USER_PUBLISH);
        }


    }
}
