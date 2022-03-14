package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private String openId;

    private String avatar;

    private String username;

    private String city;

    private String district;

    private String province;

    private String unionId;

    private Short gender;

    private String school;

    private String signature;

    private Integer followNum;

    private Integer fansNum;

    private Integer publishNum;

    private Integer collectNum;

    private Integer voteNum;

    private Integer receiveVoteNum;

    private String address;

    private String sessionId;

    private Date birthday;

    private String year;

    private String role;

    private String college;

    private String certifyImgs;

    private Short userVerify;

    private Short votePush;

    private Short replyPush;

    private Short chatPush;

    private String uuid;

    private Short integral;

    private String badge;

    private static final long serialVersionUID = 1L;
}