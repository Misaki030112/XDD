package com.hznu.xdd.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hznu.xdd.pojo.UserDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserPageVO  extends BasePageVO {


    @Data
    @Accessors(chain = true)
    public static class UserVO implements Serializable {
        private Integer id;
    
        private String avatar;
    
        private String nickname;
    
        private Short gender;
    
        private String role;
    
        private Date join_time;
    
        private boolean focus;
    
    }

    @Data
    public static class UserInfoVO implements Serializable {
        private Integer id;
    
        private Date create_time;
    
        private Date update_time;
    
        private Boolean is_delete;
    
        private String avatar;
    
        private String nickname;
    
        private Short gender;
    
        @DateTimeFormat(pattern="yyyy-MM-dd")
        @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
        private Date birthday;
    
        private String province;
    
        private String city;
    
        private String district;
    
        private String address;
    
        private String school;
    
        private String college;
    
        private String signature;
    
        private Integer follow_num;
    
        private Integer fan_num;
    
        private Integer publish_num;
    
        private Integer collect_num;
    
        private Integer vote_num;
    
        private Integer receive_vote_num;
    
        private Integer account_status;
    
        private Integer integral;
    
        private String access_year;
    
        private String role;
    
        private String subscribe_scene;
    
        private String verify_method;
    
        public UserInfoVO() {
        }
    
        public UserInfoVO(UserDO userDO) {
            this.access_year=userDO.getAccess_year();
            this.account_status=userDO.getAccount_status();
            this.address=userDO.getAddress();
            this.avatar=userDO.getAvatar();
            this.birthday=userDO.getBirthday();
            this.city=userDO.getCity();
            this.college=userDO.getCollege();
            this.create_time=userDO.getCreate_time();
            this.district=userDO.getDistrict();
            this.follow_num=userDO.getFollow_num();
            this.fan_num=userDO.getFan_num();
            this.publish_num=userDO.getPublish_num();
            this.collect_num=userDO.getCollect_num();
            this.vote_num=userDO.getVote_num();
            this.receive_vote_num=userDO.getReceive_vote_num();
            this.integral=userDO.getIntegral();
            this.id=userDO.getId();
            this.is_delete=userDO.getIs_delete();
            this.nickname=userDO.getNickname();
            this.province=userDO.getProvince();
            this.role=userDO.getRole();
            this.signature=userDO.getSignature();
            this.subscribe_scene=userDO.getSubscribe_scene();
            this.update_time=userDO.getUpdate_time();
            this.verify_method=userDO.getVerify_method();
            this.school=userDO.getSchool();
        }
    }


    public static class UserFocusInfoVO extends UserInfoVO implements Serializable {
    
        private Boolean is_focus;
    
        public Boolean getIs_focus() {
            return is_focus;
        }
    
        public void setIs_focus(Boolean is_focus) {
            this.is_focus = is_focus;
        }
    
        public UserFocusInfoVO(UserInfoVO userInfoVO) {
            this.setId(userInfoVO.getId());
            this.setNickname(userInfoVO.getNickname());
            this.setAvatar(userInfoVO.getAvatar());
            this.setGender(userInfoVO.getGender());
            this.setBirthday(userInfoVO.getBirthday());
            this.setSignature(userInfoVO.getSignature());
            this.setAddress(userInfoVO.getAddress());
            this.setCreate_time(userInfoVO.getCreate_time());
            this.setUpdate_time(userInfoVO.getUpdate_time());
            this.setIs_delete(userInfoVO.getIs_delete());
            this.setCity(userInfoVO.getCity());
            this.setAccess_year(userInfoVO.getAccess_year());
            this.setAccount_status(userInfoVO.getAccount_status());
            this.setFollow_num(userInfoVO.getFollow_num());
            this.setFan_num(userInfoVO.getFan_num());
            this.setDistrict(userInfoVO.getDistrict());
            this.setProvince(userInfoVO.getProvince());
            this.setPublish_num(userInfoVO.getPublish_num());
            this.setIntegral(userInfoVO.getIntegral());
            this.setReceive_vote_num(userInfoVO.getReceive_vote_num());
            this.setRole(userInfoVO.getRole());
            this.setSchool(userInfoVO.getSchool());
            this.setCollege(userInfoVO.getCollege());
            this.setSubscribe_scene(userInfoVO.getSubscribe_scene());
            this.setVerify_method(userInfoVO.getVerify_method());
            this.setVote_num(userInfoVO.getVote_num());
        }
    
        public UserFocusInfoVO() {
        }
    }
    
}

