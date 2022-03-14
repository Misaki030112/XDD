package com.hznu.xdd.config.weixin;

import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Objects;

/**
 * 微信小程序登录的认证提供类
 * @author  Misaki
 */
public class WeiXinMiniProgramAuthenticationProvider implements AuthenticationProvider {

    @Setter
    @Getter
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WeiXinMiniProgramAuthenticationToken weiXinMiniProgramAuthenticationToken =
                (WeiXinMiniProgramAuthenticationToken)authentication;
        UserDO userDO = userService.getUserByWxOpenId(weiXinMiniProgramAuthenticationToken.getOpenId());
        if(Objects.isNull(userDO))
        {
            throw new WeiXinMiniProgramAuthenticationException("未发现可用的用户信息");
        }
        WeiXinMiniProgramAuthenticationToken weiXinMiniProgramAuthenticationToken1 =
                new WeiXinMiniProgramAuthenticationToken(weiXinMiniProgramAuthenticationToken.getPrincipal(),weiXinMiniProgramAuthenticationToken.getCredentials(),null);
        weiXinMiniProgramAuthenticationToken1.setDetails(weiXinMiniProgramAuthenticationToken.getDetails());
        return weiXinMiniProgramAuthenticationToken1;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return WeiXinMiniProgramAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
