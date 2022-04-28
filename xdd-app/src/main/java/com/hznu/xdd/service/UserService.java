package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.reportDto;
import com.hznu.xdd.domain.VO.Collect_ugc_VO;
import com.hznu.xdd.domain.VO.CommentedVO;
import com.hznu.xdd.domain.VO.UserPageVO;
import com.hznu.xdd.domain.VO.Vote_ugc_LogVO;
import com.hznu.xdd.pojo.UserDO;
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

    UserDO getUserByUnionId(String unionId);

    void updateUser(UserDO userDO);

    boolean UpdateSessionKey(Authentication authentication,String code);

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

    UserDO changeUserInfo(String wxOpenId, String nickName, String avatar, String signature, Date birthday, String province, String city, String district,Short gender);

    /**
     * 获取我关注的人
     * @param wxOpenId
     * @param page
     * @param offset
     * @return
     */
    UserPageVO getFocusUser(String wxOpenId, Integer page, Integer offset);

    /**
     * 获取关注我的人
     * @param wxOpenId
     * @param page
     * @param offset
     * @return
     */
    UserPageVO getFocusedUser(String wxOpenId,Integer page,Integer offset);

    /**
     * 关注，取消关注某个人
     * @param wxOpenId
     * @param user_id
     * @param status
     * @return
     */
    boolean FocusUser(String wxOpenId,Integer user_id,boolean status);

    UserPageVO getVoteUgcLog(String wxOpenId, Integer page, Integer offset);

    UserPageVO getCommentUgcLog(String wxOpenId, Integer page, Integer offset);

    UserPageVO getCollectUgcLog(String wxOpenId, Integer page, Integer offset);

    boolean bindPhone(String wxOpenId,String encryptedData,String iv,String code,String sessionKey);

    int verifyStudent(String wxOpenId);

    boolean isFocusWxOffical(String wxOpenId);

    boolean finishQuestion(String wxOpenId);

    boolean isIFocusSomePeople(Integer Id);
}
