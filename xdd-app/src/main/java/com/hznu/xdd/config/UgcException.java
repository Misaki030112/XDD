package com.hznu.xdd.config;

import org.springframework.security.core.AuthenticationException;

/**
 * 评评论以及新建ugc的异常
 */
public class UgcException extends AuthenticationException {
    public UgcException(String msg, Throwable t) {
        super(msg, t);
    }

    public UgcException(String msg) {
        super(msg);
    }
}
