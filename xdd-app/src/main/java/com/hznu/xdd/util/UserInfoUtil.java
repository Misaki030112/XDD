package com.hznu.xdd.util;

import com.hznu.xdd.pojo.AdminDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 专门用来解析SpringSecurity中Authentication对象
 * @author Misaki
 */
@Component
public class UserInfoUtil {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserService userService;

    /**
     * 获取用户的WxOpenId
     */
    public static String getWxOpenIdXiaododoMini(Authentication authentication){
        UserDO userDO= (UserDO) authentication.getPrincipal();
        return userDO.getOpen_id_xiaododo_mini();
    }


    public Integer getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDO){
            UserDO userDO= (UserDO) principal;
            UserDO userByWxOpenId = userService.getUserByWxOpenId(userDO.getOpen_id_xiaododo_mini());
            return userByWxOpenId.getId();
        }
        if(principal instanceof AdminDO){
            AdminDO adminDO=(AdminDO) principal;
        }
        return -1;
    }

    /**
     * 获取用户的SessionKey
     */
    public  String getSessionKey(Authentication authentication){
        String wxOpenIdXiaododoMini = getWxOpenIdXiaododoMini(authentication);
       return (String) redisUtil.get(wxOpenIdXiaododoMini);
    }

    public  void updateSessionKey(Authentication authentication,String sessionKey){
        String wxOpenIdXiaododoMini = getWxOpenIdXiaododoMini(authentication);
        redisUtil.set(wxOpenIdXiaododoMini,sessionKey);
    }

}
