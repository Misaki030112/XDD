package com.hznu.xdd.util;

import com.hznu.xdd.pojo.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 专门用来解析SpringSecurity中Authentication对象
 * @author Misaki
 */
@Component
public class UserInfoUtil {

    @Autowired
    RedisUtil redisUtil;

    /**
     * 获取用户的WxOpenId
     */
    public static String getWxOpenIdXiaododoMini(Authentication authentication){
        UserDO userDO= (UserDO) authentication.getPrincipal();
        return userDO.getOpen_id_xiaododo_mini();
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
