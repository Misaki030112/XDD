package com.hznu.xdd.domain.Dto;

import lombok.Data;

@Data
public class MessageDto {
    private String signature;
    private String timestamp;
    private String nonce;
    private String openid;
    private String echostr;
    private String msg_signature;
    private String encrypt_type;
}
