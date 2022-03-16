package com.hznu.xdd.service.impl;


import com.hznu.xdd.dao.verify_emailDOMapper;
import com.hznu.xdd.service.MailService;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    verify_emailDOMapper verify_emailDOMapper;

    @Autowired
    UserService userService;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${validCode.length}")
    private Integer ValidCodeLength;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public boolean SendValidCode(String email,String wxOpenId) throws MessagingException {
        String validCode = VerifyCodeUtil.generateChar(ValidCodeLength);
        if(userService.VerifyMailStudent(wxOpenId,email,validCode)){
            SendMail(email,validCode);
            return true;
        }else return false;
    }


    private void SendMail(String email,String validCode) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject("验证码");
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText("<div>"+validCode+"</div>");
        javaMailSender.send(mimeMessageHelper.getMimeMessage());
    }
}
