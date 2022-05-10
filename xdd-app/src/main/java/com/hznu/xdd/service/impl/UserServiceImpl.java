package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.config.weixin.WeiXinMiniProgramAuthenticationFilter;
import com.hznu.xdd.dao.*;
import com.hznu.xdd.domain.Dto.reportDto;
import com.hznu.xdd.domain.VO.Collect_ugc_VO;
import com.hznu.xdd.domain.VO.CommentedVO;
import com.hznu.xdd.domain.VO.UserPageVO;
import com.hznu.xdd.domain.VO.Vote_ugc_LogVO;
import com.hznu.xdd.domain.pojoExam.*;
import com.hznu.xdd.pojo.*;
import com.hznu.xdd.service.UGCService;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.UserInfoUtil;
import com.hznu.xdd.utils.DateUtil;
import com.hznu.xdd.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.*;
import java.util.stream.Collectors;

@Service("xddUserService")
@Slf4j
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
    @Autowired
    voteLogDOMapper vote_logDOMapper;
    @Autowired
    UgcDOMapper ugcDOMapper;
    @Autowired
    UGCService ugcService;
    @Autowired
    ugcCommentDOMapper ugcCommentDOMapper;
    @Autowired
    collectLogDOMapper collectLogDOMapper;
    @Autowired
    focusLogDOMapper focusLogDOMapper;
    @Autowired
    UserInfoUtil userInfoUtil;


    @Value("${validCode.time}")
    private Integer expire_time;


    @Override
    public UserDetails loadUserByUsername(String wxOpenId) throws UsernameNotFoundException {
        UserDO user = getUserByWxOpenId(wxOpenId);
        return user;
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
        int i = userDOMapper.addNewUser(userDO);
        return i>0;
    }

    @Override
    public UserDO initUserInfoByWxOpenId(String wxOpenId, String encryptedData, String iv,String sessionKey)throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidParameterSpecException, BadPaddingException, InvalidKeyException {
       JSONObject jsonObject = JSON.parseObject(WeChatUtil.decryptData(encryptedData, sessionKey, iv));
        UserDO userDO = getUserByWxOpenId(wxOpenId);
        log.info("jsonObject:{}",jsonObject);
        if(userDO.getAvatar()==null|| Objects.equals(userDO.getAvatar(), ""))
            userDO.setAvatar(jsonObject.getString("avatarUrl"));
        if(userDO.getNickname()==null||Objects.equals(userDO.getNickname(), ""))
            userDO.setNickname(jsonObject.getString("nickName"));
        if(userDO.getGender()==null)
            userDO.setGender(jsonObject.getShort("gender"));
        if(userDO.getProvince()==null||Objects.equals(userDO.getProvince(), "") )
            userDO.setProvince(jsonObject.getString("province"));
        if(userDO.getCity()==null||Objects.equals(userDO.getCity(), "") )
            userDO.setCity(jsonObject.getString("city"));
        userDOMapper.updateByPrimaryKey(userDO);

        return userDO;
    }


    @Override
    public UserDO getUserById(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserDO getUserByOfficialWxOpenId(String wxOpenId) {
        UserDOExample example = new UserDOExample();
        UserDOExample.Criteria criteria = example.createCriteria();
        criteria.andOpen_id_xiaododo_official_accountEqualTo(wxOpenId);
        List<UserDO> userDOS = userDOMapper.selectByExample(example);
        assert userDOS.size()==1;
        return userDOS.get(0);
    }

    @Override
    public UserDO getUserByUnionId(String unionId) {
        UserDOExample example = new UserDOExample();
        UserDOExample.Criteria criteria = example.createCriteria();
        criteria.andUnion_idEqualTo(unionId);
        List<UserDO> userDOS = userDOMapper.selectByExample(example);
        assert userDOS.size()==1;
        return userDOS.get(0);
    }

    @Override
    public void updateUser(UserDO userDO) {
        userDOMapper.updateByPrimaryKey(userDO);
    }

    @Override
    public boolean UpdateSessionKey(Authentication authentication, String code) {
        try {
            String url = WeiXinMiniProgramAuthenticationFilter.getUrl(code);
            ResponseEntity<String> wxOpenid = restTemplate.getForEntity(url,String.class);
            //通过openid 获取用户信息
            String sessionKey = JSON.parseObject(wxOpenid.getBody()).getString("session_key");
            if(sessionKey==null) return false;
            System.out.println(userInfoUtil.getSessionKey(authentication));
            userInfoUtil.updateSessionKey(authentication,sessionKey);
            System.out.println(userInfoUtil.getSessionKey(authentication));
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
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
        criteria.andNicknameLike("%"+nickName+"%");
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
                .setParams(reportDto.getParams())
                .setCreate_user_id(userInfoUtil.getUserId())
                .setCreate_time(new Date())
                .setUpdate_time(new Date())
                .setIs_delete(false)
                .setStatus("0");
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
        UserDO user = getUserByWxOpenId(wxOpenId);
        user.setAccount_status(1);
        int user_id = user.getId();
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
    public UserDO changeUserInfo(String wxOpenId,String nickName, String avatar, String signature, Date birthday, String province, String city, String district,Short gender) {
        UserDO user = getUserByWxOpenId(wxOpenId);
        if(nickName!=null&& !nickName.equals(""))
            user.setNickname(nickName);
        if(avatar!=null&& !avatar.equals(""))
            user.setAvatar(avatar);
        if(signature!=null&& !signature.equals(""))
            user.setSignature(signature);
        if(birthday!=null)
            user.setBirthday(birthday);
        if(province!=null&& !province.equals(""))
            user.setProvince(province);
        if(city!=null&& !city.equals(""))
            user.setCity(city);
        if(district!=null&& !district.equals(""))
            user.setDistrict(district);
        if(gender!=null)
            user.setGender(gender);


        int i = userDOMapper.updateByPrimaryKey(user);
        return i>0?user:null;
    }

    @Override
    public UserPageVO getFocusUser(String wxOpenId, Integer page, Integer offset) {
        int user_id = getUserByWxOpenId(wxOpenId).getId();
        focusLogDOExample focusLogDOExample = new focusLogDOExample();
        com.hznu.xdd.domain.pojoExam.focusLogDOExample.Criteria criteria = focusLogDOExample.createCriteria();
        criteria.andUser_idEqualTo(user_id);
        criteria.andIs_deleteEqualTo(false);
        long total = focusLogDOMapper.countByExample(focusLogDOExample);

        focusLogDOExample.page(page,offset);
        focusLogDOExample.setOrderByClause("update_time desc");
        List<focusLogDO> focusLogDOS = focusLogDOMapper.selectByExample(focusLogDOExample);

        List<UserDO> userDOS = new ArrayList<>();
        focusLogDOS.forEach((f)->{
            userDOS.add(getUserById(f.getFocus_to_id()));
        });
        UserPageVO userPageVO = new UserPageVO();
        userPageVO.setList(userDOS);
        userPageVO.setTotal(total);
        return  userPageVO;
    }

    @Override
    public UserPageVO getFocusedUser(String wxOpenId, Integer page, Integer offset) {
        int user_id = getUserByWxOpenId(wxOpenId).getId();
        focusLogDOExample focusLogDOExample = new focusLogDOExample();
        com.hznu.xdd.domain.pojoExam.focusLogDOExample.Criteria criteria = focusLogDOExample.createCriteria();
        criteria.andFocus_to_idEqualTo(user_id);
        criteria.andIs_deleteEqualTo(false);
        long total = focusLogDOMapper.countByExample(focusLogDOExample);
        focusLogDOExample.page(page,offset);
        focusLogDOExample.setOrderByClause("update_time desc");
        List<focusLogDO> focusedLogDOS = focusLogDOMapper.selectByExample(focusLogDOExample);
        List<UserDO> userDOS = new ArrayList<>();
        focusedLogDOS.forEach((f)->{
            userDOS.add(getUserById(f.getUser_id()));
        });
        UserPageVO userPageVO = new UserPageVO();
        userPageVO.setList(userDOS);
        userPageVO.setTotal(total);
        return userPageVO;
    }

    @Override
    public synchronized  boolean FocusUser(String wxOpenId, Integer user_id, boolean status) {

            try {
                UserDO actionUser = getUserByWxOpenId(wxOpenId);
                int id = actionUser.getId();
                UserDO FocusedUser = getUserById(user_id);

                focusLogDOExample focusLogDOExample = new focusLogDOExample();
                com.hznu.xdd.domain.pojoExam.focusLogDOExample.Criteria criteria = focusLogDOExample.createCriteria();
                criteria.andFocus_to_idEqualTo(user_id);
                criteria.andUser_idEqualTo(id);
                List<focusLogDO> focusLogDOs = focusLogDOMapper.selectByExample(focusLogDOExample);
                focusLogDO focusLogDO = null;

                if (focusLogDOs.size() > 0) {
                    focusLogDO = focusLogDOs.get(0);
                    if (!status && !focusLogDO.getIs_delete()) {
                        focusLogDO.setIs_delete(true);
                        focusLogDO.setUpdate_time(new Date());
                        int i = focusLogDOMapper.updateByPrimaryKey(focusLogDO);
                        actionUser.setFollow_num(actionUser.getFollow_num() - 1);
                        FocusedUser.setFan_num(FocusedUser.getFan_num() - 1);
                        int j = userDOMapper.updateByPrimaryKey(actionUser);
                        int k = userDOMapper.updateByPrimaryKey(FocusedUser);
                        return i > 0 && j > 0 && k > 0;
                    } else if (status && focusLogDO.getIs_delete()) {
                        focusLogDO.setIs_delete(false);
                        focusLogDO.setUpdate_time(new Date());
                        int i = focusLogDOMapper.updateByPrimaryKey(focusLogDO);
                        actionUser.setFollow_num(actionUser.getFollow_num() + 1);
                        FocusedUser.setFan_num(FocusedUser.getFan_num() + 1);
                        int j = userDOMapper.updateByPrimaryKey(actionUser);
                        int k = userDOMapper.updateByPrimaryKey(FocusedUser);
                        return i > 0 && j > 0 && k > 0;
                    } else return false;
                } else {
                    if (status) {
                        focusLogDO = new focusLogDO();
                        focusLogDO.setUser_id(id);
                        focusLogDO.setFocus_to_id(user_id);
                        focusLogDO.setIs_delete(false);
                        focusLogDO.setCreate_time(new Date());
                        focusLogDO.setUpdate_time(new Date());
                        int i = focusLogDOMapper.insert(focusLogDO);
                        actionUser.setFollow_num(actionUser.getFollow_num() + 1);
                        FocusedUser.setFan_num(FocusedUser.getFan_num() + 1);
                        int j = userDOMapper.updateByPrimaryKey(actionUser);
                        int k = userDOMapper.updateByPrimaryKey(FocusedUser);
                        return i > 0 && j > 0 && k > 0;
                    } else return false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }

    }

    @Override
    public UserPageVO getVoteUgcLog(String wxOpenId, Integer page, Integer offset) {
        int user_id = getUserByWxOpenId(wxOpenId).getId();
        UgcDOExample ugcDOExample = new UgcDOExample();
        UgcDOExample.Criteria criteria = ugcDOExample.createCriteria();
        criteria.andUser_idEqualTo(user_id);
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(ugcDOExample);
        HashMap<Integer, UgcDO> ugcDOHashMap = new HashMap<>();
        ugcDOS.forEach((u)->{ugcDOHashMap.put(u.getId(),u);});
        List<voteLogDO> voteLogDOS = new ArrayList<>();
        ugcDOS.forEach((u)->{
            voteLogDOExample voteLogDOExample = new voteLogDOExample();
            com.hznu.xdd.domain.pojoExam.voteLogDOExample.Criteria criteria1 = voteLogDOExample.createCriteria();
            criteria1.andVote_typeEqualTo("ugc");
            criteria1.andVote_to_idEqualTo(u.getId());
            criteria1.andIs_deleteEqualTo(false);
            voteLogDOS.addAll(vote_logDOMapper.selectByExample(voteLogDOExample));
        });

        voteLogDOS.sort((a,b)->{
            return DateUtil.dateDiff(b.getCreate_time(),a.getCreate_time()).compareTo(BigDecimal.ZERO);
        });

        long total=voteLogDOS.size();
        List<voteLogDO> collect = voteLogDOS.stream().skip(page * offset).limit(offset).collect(Collectors.toList());

        List<Vote_ugc_LogVO> vote_ugc_logVOS = new ArrayList<>();
        collect.forEach((c)->{
            Vote_ugc_LogVO vote_ugc_logVO = new Vote_ugc_LogVO();
            vote_ugc_logVO.setId(c.getId())
                    .setCreate_time(c.getCreate_time())
                    .setUser_info(userDOMapper.selectByPrimaryKey(c.getUser_id()))
                    .setTo(ugcDOHashMap.get(c.getVote_to_id()));
            vote_ugc_logVOS.add(vote_ugc_logVO);
        });

        UserPageVO userPageVO = new UserPageVO();
        userPageVO.setList(vote_ugc_logVOS);
        userPageVO.setTotal(total);
        return userPageVO;
    }

    @Override
    public UserPageVO getCommentUgcLog(String wxOpenId, Integer page, Integer offset) {
        int user_id = getUserByWxOpenId(wxOpenId).getId();

        UgcDOExample example = new UgcDOExample();
        UgcDOExample.Criteria criteria1 = example.createCriteria();
        criteria1.andUser_idEqualTo(user_id);
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(example);
        HashMap<Integer, UgcDO> ugcDOHashMap = new HashMap<>();
        ugcDOS.forEach((u)->{ugcDOHashMap.put(u.getId(), u);});
        List<ugcCommentDO> ugcCommentDOS = new ArrayList<>();
        ugcDOS.forEach((u)->{
            ugcCommentDOExample ugcCommentDOExample = new ugcCommentDOExample();
            com.hznu.xdd.domain.pojoExam.ugcCommentDOExample.Criteria criteria = ugcCommentDOExample.createCriteria();
            criteria.andUgc_idEqualTo(u.getId());
            criteria.andIs_deleteEqualTo(false);
            ugcCommentDOS.addAll(ugcCommentDOMapper.selectByExample(ugcCommentDOExample));
        });

        ugcCommentDOS.sort((a,b)->{
            return DateUtil.dateDiff(b.getCreate_time(),a.getCreate_time()).compareTo(BigDecimal.ZERO);
        });

        long total=ugcCommentDOS.size();
        List<ugcCommentDO> collect = ugcCommentDOS.stream().skip(page * offset).limit(offset).collect(Collectors.toList());

        List<CommentedVO> commentedVOS = new ArrayList<>();
        collect.forEach((u)->{
            CommentedVO commentedVO = new CommentedVO();
            commentedVO.setId(u.getId())
                    .setCreate_time(u.getCreate_time())
                    .setUser_info(userDOMapper.selectByPrimaryKey(u.getUser_id()))
                    .setTo(ugcDOHashMap.get(u.getUgc_id()));
            commentedVOS.add(commentedVO);
        });
        UserPageVO userPageVO = new UserPageVO();
        userPageVO.setList(commentedVOS);
        userPageVO.setTotal(total);
        return userPageVO;
    }

    @Override
    public UserPageVO getCollectUgcLog(String wxOpenId, Integer page, Integer offset) {
        int user_id = getUserByWxOpenId(wxOpenId).getId();
        UgcDOExample example = new UgcDOExample();
        UgcDOExample.Criteria criteria1 = example.createCriteria();
        criteria1.andUser_idEqualTo(user_id);
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(example);
        HashMap<Integer, UgcDO> ugcDOHashMap = new HashMap<>();
        ugcDOS.forEach((u)->{ugcDOHashMap.put(u.getId(), u);});

        List<collectLogDO> collectLogDOS = new ArrayList<>();
        ugcDOS.forEach((u)->{
            collectLogDOExample collectLogDOExample = new collectLogDOExample();
            com.hznu.xdd.domain.pojoExam.collectLogDOExample.Criteria criteria = collectLogDOExample.createCriteria();
            criteria.andCollect_typeEqualTo("ugc");
            criteria.andCollect_to_idEqualTo(u.getId());
            criteria.andIs_deleteEqualTo(false);
            collectLogDOS.addAll(collectLogDOMapper.selectByExample(collectLogDOExample));
        });

        collectLogDOS.sort((a,b)->{
            return DateUtil.dateDiff(b.getCreate_time(),a.getCreate_time()).compareTo(BigDecimal.ZERO);
        });

        long total =collectLogDOS.size();
        List<collectLogDO> collect = collectLogDOS.stream().skip(page * offset).limit(offset).collect(Collectors.toList());

        List<Collect_ugc_VO> collect_ugc_vos = new ArrayList<>();
        collect.forEach((c)->{
            Collect_ugc_VO collect_ugc_vo = new Collect_ugc_VO();
            collect_ugc_vo.setId(c.getId())
                    .setCreate_time(c.getCreate_time())
                    .setUser_info(userDOMapper.selectByPrimaryKey(c.getUser_id()))
                    .setTo(ugcDOHashMap.get(c.getCollect_to_id()));
            collect_ugc_vos.add(collect_ugc_vo);
        });
        UserPageVO userPageVO = new UserPageVO();
        userPageVO.setTotal(total);
        userPageVO.setList(collect_ugc_vos);

        return userPageVO;
    }


    @Override
    public boolean bindPhone(String wxOpenId, String encryptedData, String iv, String code,String sessionKey) {
        if(code!=null){
            try {
                ResponseEntity<String> entity = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa9d951513d7ca374&secret=d992c9c01bb01269624071b165ba99f3", String.class);
                String access_token = JSONObject.parseObject(entity.getBody()).getString("access_token");
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("code",code);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(paramMap,headers);
                ResponseEntity<String> entity1 = restTemplate.postForEntity("https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + access_token, httpEntity, String.class);
                JSONObject phone_info = JSONObject.parseObject(entity1.getBody()).getJSONObject("phone_info");
                if(phone_info==null) return  false;
                String phoneNumber=phone_info.getString("purePhoneNumber");
                UserDO user = getUserByWxOpenId(wxOpenId);
                user.setPhone(phoneNumber);
                int i = userDOMapper.updateByPrimaryKey(user);

                userInfoUtil.RefreshUserInRedis(user,SecurityContextHolder.getContext().getAuthentication());

                return i>0;
            }catch(Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }else{
            try {
                JSONObject jsonObject = JSONObject.parseObject(WeChatUtil.decryptData(encryptedData, sessionKey, iv));
                String phoneNumber = jsonObject.getString("purePhoneNumber");
                UserDO user = getUserByWxOpenId(wxOpenId);
                user.setPhone(phoneNumber);

                userInfoUtil.RefreshUserInRedis(user,SecurityContextHolder.getContext().getAuthentication());

                int i = userDOMapper.updateByPrimaryKey(user);
                return i>0;
            }catch(Exception e){
                System.out.println(e.getMessage());
                return false;
            }

        }
    }

    @Override
    public int verifyStudent(String wxOpenId) {
        UserDO user = getUserByWxOpenId(wxOpenId);
        if(user.getPhone()!=null&&user.getVerify_method()!=null&&!Objects.equals(user.getVerify_method(), "")){
            user.setAccount_status(2);
            userDOMapper.updateByPrimaryKey(user);
            userInfoUtil.RefreshUserInRedis(user,SecurityContextHolder.getContext().getAuthentication());
            return 2;
        }else if(user.getPhone()!=null&&(user.getVerify_method()==null||Objects.equals(user.getVerify_method(), ""))){
            user.setAccount_status(1);
            userDOMapper.updateByPrimaryKey(user);
            userInfoUtil.RefreshUserInRedis(user,SecurityContextHolder.getContext().getAuthentication());
            return 1;
        }else return 0;
    }

    @Override
    public boolean isFocusWxOffical(String wxOpenId) {
        UserDO user = getUserByWxOpenId(wxOpenId);
        return !Objects.equals(user.getOpen_id_xiaododo_official_account(), "") && user.getOpen_id_xiaododo_official_account() != null;
    }

    @Override
    public boolean finishQuestion(String wxOpenId) {
        UserDO user = getUserByWxOpenId(wxOpenId);
        user.setVerify_method("question");
        int i = userDOMapper.updateByPrimaryKeySelective(user);
        if(i>0)
            userInfoUtil.RefreshUserInRedis(user,SecurityContextHolder.getContext().getAuthentication());
        return i>0;
    }

    @Override
    public boolean isIFocusSomePeople(Integer Id) {
        String userWxopenId = userInfoUtil.getWxOpenIdXiaododoMini(SecurityContextHolder.getContext().getAuthentication());
        int user_id = getUserByWxOpenId(userWxopenId).getId();

        focusLogDOExample focusLogDOExample = new focusLogDOExample();
        com.hznu.xdd.domain.pojoExam.focusLogDOExample.Criteria criteria = focusLogDOExample.createCriteria();
        criteria.andFocus_to_idEqualTo(Id);
        criteria.andUser_idEqualTo(user_id);
        criteria.andIs_deleteEqualTo(false);
        long total = focusLogDOMapper.countByExample(focusLogDOExample);
        return total>0;
    }



}
