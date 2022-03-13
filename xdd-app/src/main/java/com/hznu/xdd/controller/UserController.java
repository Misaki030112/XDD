package com.hznu.xdd.controller;

import com.hznu.xdd.domain.Result;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/test")
    public String test(Authentication authentication, HttpServletResponse response) {
        UserDO principal = (UserDO) authentication.getPrincipal();
        System.out.println("11");
        return principal.getOpenId();
    }

    @PostMapping(value= "/post/user/info", produces = { "application/json;charset=UTF-8" })
    public Result userInfo(@RequestParam(value = "encryptedData",required = false)String encryptedData,
                            @RequestParam(value="iv",required = false)String iv,
                            Authentication authentication){
        try {
            UserDO userDO = userService.initUserInfoByWxOpenId(UserInfoUtil.getWxOpenId(authentication),
                    encryptedData, iv, UserInfoUtil.getSessionKey(authentication));
            return Result.ok(userDO,"登录成功！");
        } catch (InvalidAlgorithmParameterException|BadPaddingException|NoSuchAlgorithmException e) {
            e.printStackTrace();
            return Result.error(null,"解密算法错误！");
        } catch (IllegalBlockSizeException|InvalidKeyException e) {
            e.printStackTrace();
            return Result.error(null,"提供的加密数据有误！");
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
            return Result.error(null,"参数传递错误！");
        }
    }


}
