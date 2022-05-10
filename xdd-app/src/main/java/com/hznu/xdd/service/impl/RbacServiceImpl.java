package com.hznu.xdd.service.impl;

import com.hznu.xdd.config.NotAuthorizationUrl;
import com.hznu.xdd.config.UgcException;
import com.hznu.xdd.pojo.AdminDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.RbacService;
import com.hznu.xdd.util.UserInfoUtil;
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


    @Autowired
    private UserInfoUtil userInfoUtil;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private List<String> authorizationUrls = new ArrayList<String>();

    private String[] authorizationUgcUrls = new String[]{"/post/ugc/create","/post/ugc/comment"};

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
            for (String url : authorizationUgcUrls) {
                if (antPathMatcher.match(url,requestURI)){
                    UserDO userDO= (UserDO) authentication.getPrincipal();
                    if (userDO.getPhone() == null || userDO.getPhone().isEmpty() || userDO.getVerify_method() == null || userDO.getVerify_method().isEmpty() || userDO.getAccount_status() != 2) {
                        throw new UgcException("账号信息错误");
                    }
                    break;
                }
            }
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
