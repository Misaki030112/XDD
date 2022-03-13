package com.hznu.xdd.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface RbacService extends InitializingBean {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
