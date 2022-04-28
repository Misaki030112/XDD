package com.hznu.xdd.domain.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class OfficialUserInfoDto implements Serializable {
    private Integer subscribe;
    private String openid;
    private String language;
    private Date subscribe_time;
    private String unionid;
    private String remark;
    private Integer groupid;
    private List<Integer> tagid_list;
    private String subscribe_scene;
    private Long qr_scene;
    private String qr_scene_str;
}
