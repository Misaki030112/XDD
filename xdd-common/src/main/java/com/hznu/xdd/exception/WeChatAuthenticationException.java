package com.hznu.xdd.exception;

import lombok.Data;

@Data
public class WeChatAuthenticationException extends RuntimeException{
    private String message;
    private Integer code;

    public WeChatAuthenticationException(Integer code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
