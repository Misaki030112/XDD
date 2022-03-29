package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.reportDto;
import com.hznu.xdd.pojo.UserDO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Date;
import java.util.List;

public interface UserService {


    UserDO getUserByWxOpenId(String wxOpenId);

    boolean addUser(UserDO userDO);

    UserDO initUserInfoByWxOpenId(String wxOpenId,String encryptedData,String iv,String sessionKey) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidParameterSpecException, BadPaddingException, InvalidKeyException;

    UserDO getUserById(Integer id);

    List<UserDO> searchUserByNickName(String nickName);

    List<UserDO> searchUserByNickName(String nickName,Integer page,Integer offset);

    boolean reportUser(reportDto reportDto);

    /**
     * 创建用户邮箱认证信息，如果已经存在则更新认证信息
     */
    boolean VerifyMailStudent(String wxOpenId,String email,String validCode);

    /**
     * 用户验证学生认证的验证码
     */
    boolean verifyStudentByCode(String code,String wxOpenId);

    /**
     * 用户验证学生认证的图片处理
     */
    boolean verifyStudentByPhotos(String[] photos,String wxOpenId);

    UserDO changeUserInfo(String wxOpenId, String nickName, String avatar, String signature, Date birthday, String province, String city, String district);


    List<UserDO> getFocusUser(String wxOpenId,Integer page,Integer offset);

    List<UserDO> getFocusedUser(String wxOpenId,Integer page,Integer offset);

}
