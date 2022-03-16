package com.hznu.xdd.service.impl;


import com.hznu.xdd.dao.verify_emailDOMapper;
import com.hznu.xdd.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    verify_emailDOMapper verify_emailDOMapper;

    @Value("${validCode.length}")
    private Integer ValidCodeLength;
    @Value("${validCode.NoNumber}")
    private boolean NoNumber;


    @Override
    public boolean SendValidCode(String email) {

        return false;
    }
}
