package com.hznu.xdd.util;

import com.hznu.xdd.pojo.UserDO;
import org.springframework.security.core.Authentication;

/**
 * 专门用来解析SpringSecurity中Authentication对象
 * @author Misaki
 */
public class UserInfoUtil {
    /**
     * 获取用户的WxOpenId
     */
    public static String getWxOpenId(Authentication authentication){
        UserDO userDO= (UserDO) authentication.getPrincipal();
        return userDO.getOpenId();
    }

    /**
     * 获取用户的SessionKey
     */
    public static String getSessionKey(Authentication authentication){
        UserDO userDO= (UserDO) authentication.getPrincipal();
        return userDO.getSessionId();
    }

}
