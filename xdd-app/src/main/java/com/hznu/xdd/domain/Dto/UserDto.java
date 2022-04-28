package com.hznu.xdd.domain.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date birthday;

    private String province;

    private String city;

    private String district;

    private Integer page;

    private Integer offset;

    private Boolean status;

    private Integer user_id;


}
