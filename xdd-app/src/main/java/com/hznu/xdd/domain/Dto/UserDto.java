package com.hznu.xdd.domain.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto{
    private Integer id;

    private String avatar;

    private String nickname;

    private String gender;

    private String role;

    private String encryptedData;

    private String iv;

    //nickName
    private String key;

    private String email;

    private String code;

    private String[] photos;

    private String signature;

    private Date birthday;

    private String province;

    private String city;

    private String district;

    private Integer page;

    private Integer offset;

    private Boolean status;

    private Integer user_id;


}
