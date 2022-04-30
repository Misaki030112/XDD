package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.MessageDto;
import com.hznu.xdd.exception.AesException;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 此接口提供微信公众号服务
 */
public interface WxOfficialService {

    /**
     * 接入时验证消息来自微信服务器
     */
    String verifyFromWechatInit(String msgSignature, String timeStamp, String nonce, String echoStr) throws AesException;

    /**
     * 验证消息来自微信服务器
     */
    boolean verifyFromWechat(String msgSignature,String timeStamp,String nonce);

    /**
     * 解析微信公众号中消息体中的消息信息
     */
    Map<String,String> analysisData(HttpServletRequest request, MessageDto msg) throws IOException, AesException, DocumentException;

    /**
     * 处理用户行为
     */
    void ProcessUserAction(Map<String,String> map, HttpServletResponse response,MessageDto messageDto) throws AesException, IOException;

    /**
     * 用户关注/取关公众号事件处理
     */
    void AttentionOfficial(Map<String,String> map,Boolean flag);




}
