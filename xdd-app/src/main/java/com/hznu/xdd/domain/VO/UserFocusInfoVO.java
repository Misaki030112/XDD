package com.hznu.xdd.domain.VO;



import java.io.Serializable;

public class UserFocusInfoVO extends UserInfoVO implements Serializable {

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
