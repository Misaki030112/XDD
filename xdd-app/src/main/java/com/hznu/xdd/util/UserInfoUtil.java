package com.hznu.xdd.util;

import com.hznu.xdd.config.weixin.WeiXinMiniProgramAuthenticationToken;
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
    public static String getWxOpenIdXiaododoMini(Authentication authentication){
        UserDO userDO= (UserDO) authentication.getPrincipal();
        return userDO.getOpen_id_xiaododo_mini();
    }

    /**
     * 获取用户的SessionKey
     */
    public static String getSessionKey(Authentication authentication){
        WeiXinMiniProgramAuthenticationToken token= (WeiXinMiniProgramAuthenticationToken) authentication;
        return (String) token.getCredentials();
    }

    public static void updateSessionKey(Authentication authentication,String sessionKey){
        ((WeiXinMiniProgramAuthenticationToken) authentication).UpdateSessionKey(sessionKey);
    }

}
