package com.hznu.xdd.config;

import org.springframework.stereotype.Component;

/**
 * 免认证URL
 * @author  Misaki
 */
@Component
public class XddNotAuthenticationUrl implements NotAuthorizationUrl{


    @Override
    public String[] getUrl() {
        return new String[]{"/post/user/creat"};
    }
}
