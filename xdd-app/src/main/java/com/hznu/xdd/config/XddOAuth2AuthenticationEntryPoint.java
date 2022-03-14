package com.hznu.xdd.config;

import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Misaki
 * 认证过程中的异常处理 都可以在这里处理
 */
@Component
public class XddOAuth2AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    private String typeName = OAuth2AccessToken.BEARER_TYPE;

    private String realmName = "oauth";

    @Override
    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    @Override
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    protected ResponseEntity<?> enhanceResponse(ResponseEntity<?> response, Exception exception) {
        HttpHeaders headers = response.getHeaders();
        String existing = null;
        if (headers.containsKey("WWW-Authenticate")) {
            existing = extractTypePrefix(headers.getFirst("WWW-Authenticate"));
        }
        StringBuilder builder = new StringBuilder();
        builder.append(typeName+" ");
        builder.append("realm=\"" + realmName + "\"");
        if (existing!=null) {
            builder.append(", "+existing);
        }
        HttpHeaders update = new HttpHeaders();
        update.putAll(response.getHeaders());
        update.set("WWW-Authenticate", builder.toString());
        if(exception instanceof UnapprovedClientAuthenticationException)
        {
            return new ResponseEntity<Object>( new Result(StatusCode.SUCCESS.getCode(),exception.getMessage()), update, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new Result(StatusCode.TOKEN_INVALID), update, HttpStatus.OK);
    }

    private String extractTypePrefix(String header) {
        String existing = header;
        String[] tokens = existing.split(" +");
        if (tokens.length > 1 && !tokens[0].endsWith(",")) {
            existing = StringUtils.arrayToDelimitedString(tokens, " ").substring(existing.indexOf(" ") + 1);
        }
        return existing;
    }
}
