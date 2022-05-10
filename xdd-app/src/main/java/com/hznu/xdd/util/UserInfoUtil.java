package com.hznu.xdd.util;

import com.hznu.xdd.config.weixin.WeiXinMiniProgramAuthenticationToken;
import com.hznu.xdd.pojo.AdminDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 专门用来解析SpringSecurity中Authentication对象
 * @author Misaki
 */
@Component
@Slf4j
public class UserInfoUtil {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserService userService;
    @Autowired
    private RedisConnectionFactory connectionFactory;

    private RedisTokenStore tokenStore;

    @PostConstruct
    public void init(){
        this.tokenStore=new RedisTokenStore(connectionFactory);
    }

    /**
     * 获取用户的WxOpenId
     */
    public  String getWxOpenIdXiaododoMini(Authentication authentication){
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

    public  String getUserPhoneNumber(Authentication authentication){
        UserDO userDO= (UserDO) authentication.getPrincipal();
        return userDO.getPhone();
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


    /**
     * 刷新用户信息进入令牌
     */
    @Async
    public synchronized void RefreshUserInRedis(UserDO userDO,Authentication authentication) {
            OAuth2AccessToken accessToken = tokenStore.getAccessToken((OAuth2Authentication) authentication);
            WeiXinMiniProgramAuthenticationToken auth = new WeiXinMiniProgramAuthenticationToken(userDO, null, null);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(((OAuth2Authentication) authentication).getOAuth2Request(), auth);
        tokenStore.removeAccessToken(accessToken);
        log.info("已经更新用户令牌缓存信息");
        tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        tokenStore.storeAccessToken(accessToken, oAuth2Authentication);
    }



}
