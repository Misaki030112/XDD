package com.hznu.xdd.config.weixin;

import com.hznu.xdd.pojo.UserDO;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Misaki
 * 处理微信小程序登录
 */
public class WeiXinMiniProgramAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 510L;
    private final Object principal;
    private Object credentials;

    public WeiXinMiniProgramAuthenticationToken(Object principal, Object credentials) {
        super((Collection) null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }

    public WeiXinMiniProgramAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

    public String getOpenId(){
        UserDO user = (UserDO) principal;
        return user.getOpen_id_xiaododo_mini();
    }




}
