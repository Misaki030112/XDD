package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UgcDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Integer userId;

    private String title;

    private String content;

    private String images;

    private String video;

    private String labels;

    private String params;

    private String category;

    private String classify;

    private Integer vote;

    private Integer exposure;

    private Integer collect;

    private Integer reply;

    private Boolean isOnline;

    private Boolean anonymous;

    private Integer pv;

    private Integer uv;

    private String uuid;

    private static final long serialVersionUID = 1L;
}