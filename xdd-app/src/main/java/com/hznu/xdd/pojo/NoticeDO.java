package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class NoticeDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Integer userId;

    private Integer noticeUserId;

    private String relationType;

    private Integer relationId;

    private String actionType;

    private Boolean haveRead;

    private String desc;

    private String image;

    private Integer parentId;

    private static final long serialVersionUID = 1L;
}