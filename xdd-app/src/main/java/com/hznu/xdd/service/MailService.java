package com.hznu.xdd.service;

import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;

public interface MailService {

    /**
     * 朝邮箱发送验证码，且更新数据库中的信息
     * @param email 邮箱地址
     * @param wxOpenId wxOpenId
     * @return 返回是否成功
     */
    @Async
    public void SendValidCode(String email,String wxOpenId) throws MessagingException;





}
