package com.hznu.xdd.config.weixin;


import org.springframework.security.core.AuthenticationException;

/**
 * @author Misaki
 * 处理 微信小程序登录时候的异常
 */
public class WeiXinMiniProgramAuthenticationException extends AuthenticationException {
    public WeiXinMiniProgramAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public WeiXinMiniProgramAuthenticationException(String msg) {
        super(msg);
    }
}
