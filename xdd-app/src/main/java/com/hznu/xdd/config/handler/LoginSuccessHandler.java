package com.hznu.xdd.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hznu.xdd.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@Component
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    /**
     * 是否允许同一时间在多个客户端登录
     */
    private Boolean allowLoginOnMultiTerminalATST = Boolean.FALSE;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

//        String header = request.getHeader("authorization");
//        System.out.println(header);
//        if (StringUtils.isBlank(header) || !header.startsWith("Basic ")) {
//            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
//        }
//
//        String[] tokens = extractAndDecodeHeader(header);
//        assert tokens.length == 2;
        String clientId = "wx";
        String clientSecret = "secret";
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
        } else if (passwordEncoder.matches(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
        }
        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "user");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        if (!allowLoginOnMultiTerminalATST) {
            String accessToken = token.getValue();
            String refreshToken = token.getRefreshToken().getValue();
            if (StringUtils.isNotBlank(accessToken)) {
                OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                if (Objects.nonNull(oAuth2AccessToken)) {
                    tokenStore.removeAccessToken(oAuth2AccessToken);
                }
            }
            if (StringUtils.isNotBlank(refreshToken)) {
                OAuth2RefreshToken oAuth2RefreshToken = tokenStore.readRefreshToken(refreshToken);
                if (Objects.nonNull(oAuth2RefreshToken)) {
                    tokenStore.removeRefreshToken(oAuth2RefreshToken);
                }
            }
            //重新生成
            token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        }

        response.getWriter().write(objectMapper.writeValueAsString(Result.ok(token,"注册成功")));

    }



    private String[] extractAndDecodeHeader(String header) throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }



}
