package com.hznu.xdd.service.impl;

import com.hznu.xdd.config.NotAuthorizationUrl;
import com.hznu.xdd.pojo.AdminDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.RbacService;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private List<String> authorizationUrls = new ArrayList<String>();

    @Setter
    @Autowired
    private Map<String, NotAuthorizationUrl> notAuthorizationUrlMap;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        String requestURI = request.getRequestURI();
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        for (String url : authorizationUrls) {
            if (antPathMatcher.match(url, requestURI)) {
                hasPermission = Boolean.TRUE;
                break;
            }
        }
        if ((principal instanceof AdminDO||principal instanceof UserDO) && !hasPermission) {
            //当前如果是已登录的用户 就可以放权
            hasPermission = Boolean.TRUE;
        } else if (principal instanceof String
                && StringUtils.equalsIgnoreCase(principal.toString(), "anonymousUser")) {

            // 如果用户是匿名用户 可以在此处校验他的权限
//            return false;
        }
        return hasPermission;

    }


    @Override
    public void afterPropertiesSet(){
        notAuthorizationUrlMap.forEach((key, value) -> authorizationUrls.addAll(Arrays.asList(value.getUrl())));
    }
}
