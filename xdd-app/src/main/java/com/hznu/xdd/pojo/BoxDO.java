package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class BoxDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private String image;

    private String appeal;

    private String college;

    private Short age;

    private Short height;

    private Short weight;

    private String constellation;

    private Date expire;

    private String contact;

    private String hobby;

    private String aboutme;

    private Integer userId;

    private Short gender;

    private Boolean isOnline;

    private static final long serialVersionUID = 1L;
}