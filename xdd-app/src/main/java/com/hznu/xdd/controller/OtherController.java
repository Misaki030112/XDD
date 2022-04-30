package com.hznu.xdd.controller;

import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.hotSearchVO;
import com.hznu.xdd.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional(rollbackFor = Exception.class)
public class OtherController {


    @Autowired
    OtherService otherService;

    @GetMapping(value = {"/get/recent_search"},produces = {"application/json;charset=UTF-8"})
    public Result getRecentSearch(){

        List<hotSearchVO> hotSearch = otherService.getHotSearch();

        return Result.ok(hotSearch,"获取成功");
    }
}
