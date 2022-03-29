package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.dao.reportDOMapper;
import com.hznu.xdd.dao.verify_emailDOMapper;
import com.hznu.xdd.dao.verify_imageDOMapper;
import com.hznu.xdd.domain.Dto.reportDto;
import com.hznu.xdd.domain.WeiXinOpenID;
import com.hznu.xdd.domain.pojoExam.UserDOExample;
import com.hznu.xdd.domain.pojoExam.verify_emailDOExample;
import com.hznu.xdd.domain.pojoExam.verify_imageDOExample;
import com.hznu.xdd.exception.WeChatAuthenticationException;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.pojo.reportDO;
import com.hznu.xdd.pojo.verify_emailDO;
import com.hznu.xdd.pojo.verify_imageDO;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.utils.DateUtil;
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
import java.util.*;

@Service("xddUserService")
public class UserServiceImpl implements UserService , UserDetailsService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    reportDOMapper reportDOMapper;

    @Autowired
    verify_emailDOMapper verify_emailDOMapper;

    @Autowired
    verify_imageDOMapper verify_imageDOMapper;

    @Value("${validCode.time}")
    private Integer expire_time;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }



    @Override
    public UserDO getUserByWxOpenId(String wxOpenId) {
        UserDOExample userDOExample = new UserDOExample();
        UserDOExample.Criteria criteria = userDOExample.createCriteria();
        criteria.andOpen_id_xiaododo_miniEqualTo(wxOpenId);
        List<UserDO> userDOS = userDOMapper.selectByExample(userDOExample);
        return userDOS.size() == 0 ? null : userDOS.get(0);
    }

    @Override
    public boolean addUser(UserDO userDO) {
        int i = userDOMapper.insert(userDO);
        return i>0;
    }

    @Override
    public UserDO initUserInfoByWxOpenId(String wxOpenId, String encryptedData, String iv,String sessionKey)throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidParameterSpecException, BadPaddingException, InvalidKeyException {
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
        criteria.andNicknameLike(nickName);
        return userDOMapper.selectByExample(example);
    }

    @Override
    public List<UserDO> searchUserByNickName(String nickName, Integer page, Integer offset) {
        UserDOExample example = new UserDOExample();
        UserDOExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameLike(nickName);
        example.page(page,offset);
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

    @Override
    public boolean VerifyMailStudent(String wxOpenId, String email, String validCode) {
        Integer user_id = getUserByWxOpenId(wxOpenId).getId();
        verify_emailDOExample example = new verify_emailDOExample();
        verify_emailDOExample.Criteria criteria = example.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        criteria.andUser_idEqualTo(user_id);
        List<verify_emailDO> verify_emailDOS = verify_emailDOMapper.selectByExample(example);
        verify_emailDO verify_emailDO;
        if(verify_emailDOS.size()>0){
            //更新validCode,email
            verify_emailDO= verify_emailDOS.get(0);
            Date date = new Date();
            verify_emailDO.setEmail(email)
                          .setUpdate_time(date)
                          .setExpire_time(DateUtil.addSecond(expire_time,date))
                          .setSession_key(validCode);
            int i = verify_emailDOMapper.updateByPrimaryKey(verify_emailDO);
            return i>0;
        }else{
            //创建用户校园邮箱验证信息
           verify_emailDO=new verify_emailDO();
            Date date = new Date();
            verify_emailDO.setUser_id(user_id)
                    .setEmail(email)
                    .setCreate_time(date)
                    .setUpdate_time(date)
                    .setExpire_time(DateUtil.addSecond(expire_time,date))
                    .setIs_delete(false)
                    .setSession_key(validCode);
            int i = verify_emailDOMapper.insert(verify_emailDO);
            return i>0;
        }

    }

    @Override
    public boolean verifyStudentByCode(String code, String wxOpenId) {
        Integer user_id = getUserByWxOpenId(wxOpenId).getId();
        verify_emailDOExample example = new verify_emailDOExample();
        verify_emailDOExample.Criteria criteria = example.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        criteria.andUser_idEqualTo(user_id);
        List<verify_emailDO> verify_emailDOS = verify_emailDOMapper.selectByExample(example);
        if(verify_emailDOS.size()>0){
            verify_emailDO verify_emailDO= verify_emailDOS.get(0);
            Date expire_time = verify_emailDO.getExpire_time();
            String session_key = verify_emailDO.getSession_key();
            return expire_time.compareTo(new Date()) >= 0 && Objects.equals(session_key, code);
        }else return false;
    }

    @Override
    public boolean verifyStudentByPhotos(String[] photos, String wxOpenId) {
        Integer user_id = getUserByWxOpenId(wxOpenId).getId();
        verify_imageDOExample verify_imageDOExample = new verify_imageDOExample();
        verify_imageDOExample.Criteria criteria = verify_imageDOExample.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        criteria.andUser_idEqualTo(user_id);
        List<verify_imageDO> verify_imageDOS = verify_imageDOMapper.selectByExample(verify_imageDOExample);
        if(verify_imageDOS.size()>0){
            verify_imageDO verify_imageDO= verify_imageDOS.get(0);
            verify_imageDO.setUpdate_time(new Date());
            verify_imageDO.setImage(Arrays.toString(photos));
            int i = verify_imageDOMapper.updateByPrimaryKey(verify_imageDO);
            return i>0;
        }else{
            verify_imageDO verify_imageDO=new verify_imageDO();
            verify_imageDO.setUser_id(user_id)
                    .setCreate_time(new Date())
                    .setUpdate_time(new Date())
                    .setIs_delete(false)
                    .setImage(Arrays.toString(photos));
            int i = verify_imageDOMapper.insert(verify_imageDO);
            return i>0;
        }
    }

    @Override
    public UserDO changeUserInfo(String wxOpenId,String nickName, String avatar, String signature, Date birthday, String province, String city, String district) {
        UserDO user = getUserByWxOpenId(wxOpenId);
        user.setNickname(nickName)
                .setAvatar(avatar)
                .setSignature(signature)
                .setBirthday(birthday)
                .setProvince(province)
                .setCity(city)
                .setDistrict(district);
        int i = userDOMapper.updateByPrimaryKey(user);
        return i>0?user:null;
    }

    @Override
    public List<UserDO> getFocusUser(String wxOpenId, Integer page, Integer offset) {
        return null;
    }

    @Override
    public List<UserDO> getFocusedUser(String wxOpenId, Integer page, Integer offset) {
        return null;
    }


}
