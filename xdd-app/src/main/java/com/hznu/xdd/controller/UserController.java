package com.hznu.xdd.controller;

import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Dto.reportDto;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value= "/post/user/info", produces = { "application/json;charset=UTF-8" })
    public Result userInfo(@RequestParam(value = "encryptedData",required = false)String encryptedData,
                            @RequestParam(value="iv",required = false)String iv,
                            Authentication authentication){
        try {
            UserDO userDO = userService.initUserInfoByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication),
                    encryptedData, iv, UserInfoUtil.getSessionKey(authentication));
            return Result.ok(userDO,"登录成功！");
        } catch (InvalidAlgorithmParameterException | BadPaddingException | NoSuchAlgorithmException | InvalidParameterSpecException e) {
            e.printStackTrace();
            return new Result(StatusCode.PARAMS_CHECK_FAILED);
        } catch (IllegalBlockSizeException|InvalidKeyException e) {
            e.printStackTrace();
            return new Result(StatusCode.INVALID_PARAMS);
        }
    }


    @PostMapping(value = "/post/user/login",produces = { "application/json;charset=UTF-8" })
    public Result login(Authentication authentication){
        UserDO userDO = userService.getUserByWxOpenId(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if(userDO==null)
            return new Result(StatusCode.NO_EXIST);
        else return Result.ok(userDO,"登录成功");
    }


    @GetMapping(value = "/get/user/info",produces = { "application/json;charset=UTF-8" })
    public Result getUserInfo(@RequestParam("id") Integer id){
        UserDO userDO = userService.getUserById(id);
        if(userDO==null)
            return new Result(StatusCode.NO_EXIST);
        else return Result.ok(userDO,"登录成功");
    }


    @GetMapping(value="/get/user",produces = { "application/json;charset=UTF-8" })
    public Result searchUserByName(@RequestParam("key") String nickName){
        List<UserDO> userDOS = userService.searchUserByNickName(nickName);
        return Result.ok(userDOS,"获取成功");
    }


    @PostMapping(value = "/post/user/report",produces = { "application/json;charset=UTF-8" })
    public Result reportUser(@RequestBody reportDto reportDto){
        boolean flag = userService.reportUser(reportDto);
        if(flag) return new Result(StatusCode.SUCCESS.getCode(),"提交成功");
        else return new Result(StatusCode.INVALID_PARAMS);
    }






}
