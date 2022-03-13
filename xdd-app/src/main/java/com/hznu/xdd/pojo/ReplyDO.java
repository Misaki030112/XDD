package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ReplyDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private String relationType;

    private Integer relationId;

    private Integer parentId;

    private String parentType;

    private Integer userId;

    private Boolean isOnline;

    private Integer vote;

    private Integer replyNum;

    private String content;

    private String images;

    private String video;

    private Boolean anonymous;

    private String uuid;

    private static final long serialVersionUID = 1L;
}