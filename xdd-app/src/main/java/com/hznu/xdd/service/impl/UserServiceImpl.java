package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.dao.reportDOMapper;
import com.hznu.xdd.domain.Dto.reportDto;
import com.hznu.xdd.domain.WeiXinOpenID;
import com.hznu.xdd.domain.pojoExam.UserDOExample;
import com.hznu.xdd.exception.WeChatAuthenticationException;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.pojo.reportDO;
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
import java.util.List;
import java.util.Map;

@Service("xddUserService")
public class UserServiceImpl implements UserService , UserDetailsService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    reportDOMapper reportDOMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }



    @Override
    public UserDO getUserByWxOpenId(String wxOpenId) {
        UserDOExample userDOExample = new UserDOExample();
        UserDOExample.Criteria criteria = userDOExample.createCriteria();
        criteria.andOpen_id_xiaododo_miniEqualTo(wxOpenId);
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
        userDO.setNickname(jsonObject.getString("nickname"))
                .setAvatar(jsonObject.getString("avatarUrl"))
                .setGender(jsonObject.getShort("gender"))
                .setProvince(jsonObject.getString("province"))
                .setCity(jsonObject.getString("city"));
        userDOMapper.updateByPrimaryKey(userDO);
        return userDO;
    }


    @Override
    public UserDO getUserById(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserDO> searchUserByNickName(String nickName) {
        UserDOExample example = new UserDOExample();
        UserDOExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(nickName);
        return userDOMapper.selectByExample(example);
    }

    //举报用户
    @Override
    public boolean reportUser(reportDto reportDto) {
        reportDO reportDO = new reportDO();
        reportDO.setReport_user_id(reportDto.getReport_user_id())
                .setReport_ugc_id(reportDto.getReport_ugc_id())
                .setReport_to_type(reportDto.getReport_to_type())
                .setParams(reportDto.getParams());
        int insert = reportDOMapper.insert(reportDO);
        return insert == 1;
    }


}
