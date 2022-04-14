package com.hznu.xdd.controller;

import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.UserVO;
import com.hznu.xdd.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping("/get/group/user")
    public Result getGroupUser(@RequestParam("id") Integer id){
        List<UserVO> groupUser = groupService.getGroupUser(id);
        return Result.ok(groupUser,"获取成功");
    }

    @PostMapping(value = "/post/group/end",produces = {"application/json;charset=UTF-8"})
    public Result groupEnd(@RequestParam("id") Integer id){
        Boolean groupEnd = groupService.groupEnd(id);
        return Result.ok(groupEnd,"停止成功");
    }
}
