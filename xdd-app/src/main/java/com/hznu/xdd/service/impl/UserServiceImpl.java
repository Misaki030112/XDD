package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.domain.WeiXinOpenID;
import com.hznu.xdd.domain.pojoExam.UserDOExample;
import com.hznu.xdd.exception.WeChatAuthenticationException;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.utils.WeChatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service("xddUserService")
public class UserServiceImpl implements UserService , UserDetailsService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserDOMapper userDOMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }



    @Override
    public UserDO getUserByWxOpenId(String wxOpenId) {
        UserDOExample userDOExample = new UserDOExample();
        UserDOExample.Criteria criteria = userDOExample.createCriteria();
        criteria.andOpenIdEqualTo(wxOpenId);
        return userDOMapper.selectByExample(userDOExample).get(0);
    }

    @Override
    public boolean addUser(UserDO userDO) {
        int i = userDOMapper.insert(userDO);
        return i>0;
    }

    @Override
    public UserDO initUserInfoByWxOpenId(String wxOpenId, String encryptedData, String iv,String sessionKey) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidKeyException {
       JSONObject jsonObject = JSON.parseObject(WeChatUtil.decryptData(encryptedData, sessionKey, iv));
        UserDO userDO = getUserByWxOpenId(wxOpenId);
        userDO.setUsername(jsonObject.getString("nickname"))
                .setAvatar(jsonObject.getString("avatarUrl"))
                .setGender(jsonObject.getShort("gender"))
                .setProvince(jsonObject.getString("province"))
                .setCity(jsonObject.getString("city"));
        userDOMapper.updateByPrimaryKey(userDO);
        return userDO;
    }


}
