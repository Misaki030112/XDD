package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.reportDto;
import com.hznu.xdd.pojo.UserDO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.List;

public interface UserService {


    UserDO getUserByWxOpenId(String wxOpenId);

    boolean addUser(UserDO userDO);

    UserDO initUserInfoByWxOpenId(String wxOpenId,String encryptedData,String iv,String sessionKey) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidKeyException;

    UserDO getUserById(Integer id);

    List<UserDO> searchUserByNickName(String nickName);

    boolean reportUser(reportDto reportDto);
}
