package com.hznu.xdd.controller;

import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Dto.GroupDto;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.UserVO;
import com.hznu.xdd.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupService groupService;


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

//    @PostMapping("/post/group/update")
//    public Result updateGroup(@RequestBody @Valid GroupDto groupDto){
//
//    }

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
        List<UserVO> groupUser = groupService.getGroupUser(id);
        return Result.ok(groupUser,"获取成功");
    }

    @PostMapping(value = "/post/group/end",produces = {"application/json;charset=UTF-8"})
    public Result groupEnd(@RequestBody GroupDto groupDto){
        Boolean groupEnd = groupService.groupEnd(groupDto.getId());
        return Result.ok(groupEnd,"停止成功");
    }
}
