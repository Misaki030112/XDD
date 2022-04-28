package com.hznu.xdd.config;

import com.hznu.xdd.config.weixin.WeiXinMiniProgramAuthenticationFilter;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler ssbAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler ssbAuthenticationFailureHandler;

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;

    @Autowired
    private XddOAuth2AuthenticationEntryPoint xddOAuth2AuthenticationEntryPoint;

    @Autowired
    private XddResponseExceptionTranslator xddResponseExceptionTranslator;;
    @Autowired
    private RedisUtil redisUtil;

    public void configure(HttpSecurity http) throws Exception {


        configWeiXinMiniProgramRules(http);

        http.csrf().disable()
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .anyRequest()
                .access("@rbacService.hasPermission(request,authentication)");
    }


    private void configWeiXinMiniProgramRules(HttpSecurity http){
        WeiXinMiniProgramAuthenticationFilter weiXinMiniProgramAuthenticationFilter =
                new WeiXinMiniProgramAuthenticationFilter();
        weiXinMiniProgramAuthenticationFilter.setAuthenticationSuccessHandler(ssbAuthenticationSuccessHandler);
        weiXinMiniProgramAuthenticationFilter.setAuthenticationFailureHandler(ssbAuthenticationFailureHandler);
        weiXinMiniProgramAuthenticationFilter.setAuthenticationManager(authenticationManager);
        weiXinMiniProgramAuthenticationFilter.setRestTemplate(restTemplate);
        weiXinMiniProgramAuthenticationFilter.setUserService(userService);
        weiXinMiniProgramAuthenticationFilter.setRedisUtil(redisUtil);
        http.addFilterAfter(weiXinMiniProgramAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
        AccessDeniedHandler accessDeniedHandler = resources.getAccessDeniedHandler();
        if(accessDeniedHandler instanceof OAuth2AccessDeniedHandler)
        {
            ((OAuth2AccessDeniedHandler) accessDeniedHandler).setExceptionTranslator(xddResponseExceptionTranslator);
        }
        //自定义认证失败，处理逻辑
        resources.authenticationEntryPoint(xddOAuth2AuthenticationEntryPoint);
    }


}
