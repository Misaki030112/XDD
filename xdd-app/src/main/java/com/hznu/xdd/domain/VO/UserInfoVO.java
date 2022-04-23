package com.hznu.xdd.domain.VO;

import com.hznu.xdd.pojo.UserDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfoVO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String avatar;

    private String nickname;

    private Short gender;

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
