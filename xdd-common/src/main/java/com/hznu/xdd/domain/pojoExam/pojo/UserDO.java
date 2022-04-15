package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String open_id_xiaododo_mini;

    private String union_id;

    private String open_id_xiaododo_official_account;

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

    private String phone;

    private String subscribe_scene;

    private String verify_method;

    private String student_email;

    private static final long serialVersionUID = 1L;
}